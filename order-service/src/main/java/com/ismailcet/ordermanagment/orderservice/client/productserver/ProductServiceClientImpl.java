package com.ismailcet.ordermanagment.orderservice.client.productserver;

import com.ismailcet.ordermanagment.orderservice.client.productserver.client.ProductServiceClient;
import com.ismailcet.ordermanagment.orderservice.client.productserver.model.CheckProductClientRequest;
import com.ismailcet.ordermanagment.orderservice.client.productserver.model.CheckProductClientResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceClientImpl {
    private final ProductServiceClient productServiceClient;

    public ProductServiceClientImpl(ProductServiceClient productServiceClient) {
        this.productServiceClient = productServiceClient;
    }

    public Boolean checkProductControl(Long productId){
        CheckProductClientRequest request = new CheckProductClientRequest();
        request.setProductId(productId);
        ResponseEntity<CheckProductClientResponse> responseEntity = productServiceClient.checkProductControl(request);
        if(responseEntity != null && responseEntity.getBody() != null){
            return responseEntity.getBody().getResult();
        }else{
            return false;
        }
    }

}
