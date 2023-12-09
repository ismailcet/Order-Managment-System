package com.ismailcet.ordermanagment.orderservice.dto;

import com.ismailcet.ordermanagment.orderservice.entity.Order;
import com.ismailcet.ordermanagment.orderservice.entity.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
    private Long orderId;
    private Long productId;
    private Long userId;
    private Status status;
    private String cargoStatus;

    public OrderDTO(Order order){
        this.productId = order.getProductId();
        this.userId = order.getUserId();
        this.status = order.getStatus();
        this.cargoStatus = order.getCargoStatus();
    }
}
