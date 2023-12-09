package com.ismailcet.ordermanagment.orderservice.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest {
    private Long productId;
    private Long userId;
    private Integer amount;
}
