package com.ismailcet.ordermanagment.orderservice.controller;

import com.ismailcet.ordermanagment.orderservice.controller.request.CreateOrderRequest;
import com.ismailcet.ordermanagment.orderservice.controller.response.CreateOrderResponse;
import com.ismailcet.ordermanagment.orderservice.dto.OrderDTO;
import com.ismailcet.ordermanagment.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "/createOrder")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request){
        CreateOrderResponse response = new CreateOrderResponse();
        OrderDTO orderDTO = orderService.createOrder(request);
        response.setOrderDTO(orderDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
