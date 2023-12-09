package com.ismailcet.ordermanagment.orderservice.controller.response;

import com.ismailcet.ordermanagment.orderservice.dto.OrderDTO;
import com.ismailcet.ordermanagment.orderservice.entity.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderResponse {
    private OrderDTO orderDTO;
}
