package com.ismailcet.ordermanagment.cargoservice.controller.request;

import com.ismailcet.ordermanagment.cargoservice.entity.CargoStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCargoStatusRequest {
    private Long cargoId;
    private CargoStatus status;
}
