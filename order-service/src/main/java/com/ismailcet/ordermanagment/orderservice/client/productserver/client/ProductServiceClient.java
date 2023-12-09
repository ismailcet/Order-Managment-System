package com.ismailcet.ordermanagment.orderservice.client.productserver.client;

import com.ismailcet.ordermanagment.orderservice.client.productserver.model.CheckProductClientRequest;
import com.ismailcet.ordermanagment.orderservice.client.productserver.model.CheckProductClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service",path = "/api/product")
public interface ProductServiceClient {
    @PostMapping(path = "checkProductId")
    public ResponseEntity<CheckProductClientResponse> checkProductControl(@RequestBody CheckProductClientRequest request);
}
