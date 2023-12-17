package com.ismailcet.ordermanagment.cargoservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ismailcet.ordermanagment.cargoservice.controller.request.UpdateCargoStatusRequest;
import com.ismailcet.ordermanagment.cargoservice.dto.CargoDTO;
import com.ismailcet.ordermanagment.cargoservice.entity.Cargo;
import com.ismailcet.ordermanagment.cargoservice.entity.CargoStatus;
import com.ismailcet.ordermanagment.cargoservice.repository.CargoRepository;
import com.ismailcet.ordermanagment.cargoservice.utils.StringToJsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class CargoService {
    @Value("${spring.kafka.producer.cargo}")
    private String cargoTopic;
    private final CargoRepository cargoRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public CargoService(CargoRepository cargoRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.cargoRepository = cargoRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "order-topic",groupId="cargo-consumer")
    private void createOrderFromKafkaConsumer(String order) throws JsonProcessingException {
        JsonNode jsonOrder = StringToJsonConverter.stringToJsonConverter(order);
        log.info("Created Order:{} ",jsonOrder);
        Cargo cargo = Cargo.builder()
                .orderId(jsonOrder.path("id").asLong())
                .userId(jsonOrder.path("userId").asLong())
                .status(CargoStatus.PREPARING)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .expectedDate(LocalDateTime.now().plusDays(7))
                .build();
        cargoRepository.save(cargo);
        log.info("Created Cargo Id: {}",cargo.getId());
    }

    public CargoDTO updateCargo(UpdateCargoStatusRequest request) throws JsonProcessingException {
        Cargo cargo = cargoRepository.findById(request.getCargoId()).orElseThrow(()->new NullPointerException("Cargo is not found ! "));
        if(!cargo.getStatus().equals(CargoStatus.DELIVERED)){
            cargo.setStatus(request.getStatus());
            cargo.setUpdatedDate(LocalDateTime.now());
            sendKafkaTemplate(cargo);
            cargoRepository.save(cargo);
            return new CargoDTO(cargo);
        }else{
            throw new NullPointerException("Cargo Already delivered ! ");
        }
    }

    private void sendKafkaTemplate(Cargo cargo) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String reqJson = mapper.writeValueAsString(cargo);
        kafkaTemplate.send(cargoTopic,reqJson);
    }
}
