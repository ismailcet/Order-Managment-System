package com.ismailcet.ordermanagment.cargoservice.controller.response;

import com.ismailcet.ordermanagment.cargoservice.dto.CargoDTO;
import com.ismailcet.ordermanagment.cargoservice.entity.CargoStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCargoStatusResponse {
    private CargoDTO cargoDTO;
}
