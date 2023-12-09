package com.ismailcet.ordermanagment.orderservice.client.userservice.client;

import com.ismailcet.ordermanagment.orderservice.client.userservice.model.CheckUserClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-information-service",path = "/api/userinformation")
public interface UserServiceClient {
    @PostMapping(path = "/checkUser")
    public ResponseEntity<CheckUserClientResponse> checkUser(@RequestBody Long userId);
}
