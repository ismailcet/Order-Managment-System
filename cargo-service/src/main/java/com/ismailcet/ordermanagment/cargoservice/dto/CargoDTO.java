package com.ismailcet.ordermanagment.cargoservice.dto;

import com.ismailcet.ordermanagment.cargoservice.entity.Cargo;
import com.ismailcet.ordermanagment.cargoservice.entity.CargoStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CargoDTO {
    private Long cargoId;
    private Long orderId;
    private Long userId;
    private CargoStatus status;

    public CargoDTO(Cargo cargo){
        this.cargoId = cargo.getId();
        this.orderId = cargo.getOrderId();
        this.userId = cargo.getUserId();
        this.status = cargo.getStatus();
    }
}
