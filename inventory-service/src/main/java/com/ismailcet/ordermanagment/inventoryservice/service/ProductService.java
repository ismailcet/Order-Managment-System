package com.ismailcet.ordermanagment.inventoryservice.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ismailcet.ordermanagment.inventoryservice.controller.request.CheckProductRequest;
import com.ismailcet.ordermanagment.inventoryservice.controller.request.CreateProductRequest;
import com.ismailcet.ordermanagment.inventoryservice.dto.Order;
import com.ismailcet.ordermanagment.inventoryservice.dto.ProductDTO;
import com.ismailcet.ordermanagment.inventoryservice.entity.Product;
import com.ismailcet.ordermanagment.inventoryservice.repository.ProductRepository;
import com.ismailcet.ordermanagment.inventoryservice.utils.StringToJsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
@Slf4j
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO createProduct(CreateProductRequest request) {
        Optional<Product> productControl =
                productRepository.findByProductName(request.getName());
        if(!productControl.isPresent()){
            Product product = Product.builder()
                    .productName(request.getName())
                    .amount(request.getAmount())
                    .build();
            productRepository.save(product);
            return new ProductDTO(product);
        }else{
            throw new NullPointerException("Eklemek istediginiz Product Mevcuttur ! ");
        }
    }

    public Boolean checkProductControl(CheckProductRequest request) {
        Optional<Product> product = productRepository.findById(request.getProductId());
        if(product.isPresent() && product.get().getAmount() > 0){
            return true;
        }else{
            return false;
        }
    }

    @KafkaListener(topics = "inventory-topic",groupId = "inventory-consumer")
    private void decreaseProductItemAfterOrder(String order) throws IOException {
        JsonNode parser = StringToJsonConverter.stringConverter(order);
        log.info("Created Order : {}",parser);
        Product product = productRepository.findById(parser.path("productId").asLong()).get();
        log.info("Decreased Product : {}",product);
        product.setAmount(product.getAmount() - 1);
        productRepository.save(product);
    }
}
