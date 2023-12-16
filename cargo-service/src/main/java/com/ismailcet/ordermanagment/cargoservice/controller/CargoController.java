package com.ismailcet.ordermanagment.cargoservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ismailcet.ordermanagment.cargoservice.controller.request.UpdateCargoStatusRequest;
import com.ismailcet.ordermanagment.cargoservice.controller.response.UpdateCargoStatusResponse;
import com.ismailcet.ordermanagment.cargoservice.dto.CargoDTO;
import com.ismailcet.ordermanagment.cargoservice.service.CargoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/cargo")
public class CargoController {
    private final CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @PostMapping(path = "updateCargoStatus")
    public ResponseEntity<UpdateCargoStatusResponse> updateCargo(@RequestBody UpdateCargoStatusRequest request) throws JsonProcessingException {
        UpdateCargoStatusResponse response = new UpdateCargoStatusResponse();
        CargoDTO dto = cargoService.updateCargo(request);
        response.setCargoDTO(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
