package com.ismailcet.ordermanagment.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ismailcet.ordermanagment.orderservice.client.productserver.ProductServiceClientImpl;
import com.ismailcet.ordermanagment.orderservice.client.userservice.UserServiceClientImpl;
import com.ismailcet.ordermanagment.orderservice.controller.request.CreateOrderRequest;
import com.ismailcet.ordermanagment.orderservice.dto.OrderDTO;
import com.ismailcet.ordermanagment.orderservice.entity.Order;
import com.ismailcet.ordermanagment.orderservice.entity.Status;
import com.ismailcet.ordermanagment.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductServiceClientImpl productServiceClient;
    private final UserServiceClientImpl userServiceClient;
    private final KafkaTemplate<String, String> kafkaTemplate;
    @Value("${spring.kafka.producer.order}")
    private String orderTopic;
    @Value("${spring.kafka.producer.inventory}")
    private String inventoryTopic;
    @Value("${spring.kafka.producer.cargo}")
    private String cargoTopic;

    public OrderService(OrderRepository orderRepository, ProductServiceClientImpl productServiceClient, UserServiceClientImpl userServiceClient, KafkaTemplate<String, String> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.productServiceClient = productServiceClient;
        this.userServiceClient = userServiceClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    public OrderDTO createOrder(CreateOrderRequest request) throws JsonProcessingException {
        Order order = new Order();
        //todo Inventory service check id control
        controlProductHave(request,order);
        //todo Check user service check id control
        controlUserIdHave(request, order);
        order.setCargoStatus("PREPARING");
        order.setStatus(Status.PREPARING);
        orderRepository.save(order);
        sendKafkaTemplate(order);
        OrderDTO orderDTO = new OrderDTO(order);
        return orderDTO;
    }

    private void sendKafkaTemplate(Order order) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String reqJson = mapper.writeValueAsString(order);
        kafkaTemplate.send(inventoryTopic, reqJson);
    }

    private void controlUserIdHave(CreateOrderRequest request, Order order) {
        if(userServiceClient.checkUserControl(request.getUserId())){
            order.setUserId(request.getUserId());
        }else{
            throw new NullPointerException("User is not found ! ");
        }
    }

    private void controlProductHave(CreateOrderRequest request,Order order) {
        if(productServiceClient.checkProductControl(request.getProductId())){
            order.setProductId(request.getProductId());
        }else{
            throw new NullPointerException("Product Id is not exist ! ");
        }
    }
}
