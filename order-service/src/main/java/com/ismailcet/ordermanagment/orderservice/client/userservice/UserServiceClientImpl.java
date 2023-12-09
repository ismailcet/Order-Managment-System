package com.ismailcet.ordermanagment.orderservice.client.userservice;

import com.ismailcet.ordermanagment.orderservice.client.userservice.client.UserServiceClient;
import com.ismailcet.ordermanagment.orderservice.client.userservice.model.CheckUserClientResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceClientImpl {
    private final UserServiceClient userServiceClient;

    public UserServiceClientImpl(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    public Boolean checkUserControl(Long userId){
        ResponseEntity<CheckUserClientResponse> responseEntity = userServiceClient.checkUser(userId);
        if(responseEntity == null || responseEntity.getBody() == null) {
            throw new NullPointerException("User is not found ! ");
        }
        return responseEntity.getBody().getResult();
    }
}
