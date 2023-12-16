package com.ismailcet.ordermanagment.userinformationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.ismailcet.ordermanagment.userinformationservice.controller.request.CreateUserRequest;
import com.ismailcet.ordermanagment.userinformationservice.dto.UserDTO;
import com.ismailcet.ordermanagment.userinformationservice.entity.Information;
import com.ismailcet.ordermanagment.userinformationservice.entity.InformationType;
import com.ismailcet.ordermanagment.userinformationservice.entity.User;
import com.ismailcet.ordermanagment.userinformationservice.repository.InformationRepository;
import com.ismailcet.ordermanagment.userinformationservice.repository.UserRepository;
import com.ismailcet.ordermanagment.userinformationservice.utils.StringToJsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class UserInformationService {
    private final UserRepository userRepository;
    private final InformationRepository informationRepository;

    public UserInformationService(UserRepository userRepository, InformationRepository informationRepository) {
        this.userRepository = userRepository;
        this.informationRepository = informationRepository;
    }

    public UserDTO createUser(CreateUserRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phoneNumber(request.getEmail())
                .build();
        userRepository.save(user);
        return new UserDTO(user);
    }

    public Boolean checkUser(Long userId) {
        Optional<User> userControl = userRepository.findById(userId);
        if(userControl.isPresent()){
            return true;
        }else{
            return false;
        }
    }

    @KafkaListener(topics = "order-topic",groupId = "user-information-consumer")
    private void sendEmailUserAboutOrder(String order) throws JsonProcessingException {
        JsonNode parser = StringToJsonConverter.stringToJsonConverter(order);
        log.info("Created Order: {}",parser);
        User user = userRepository.findById(parser.path("userId").asLong()).get();
        Information information = Information.builder()
                .user(user)
                .orderId(parser.path("id").asLong())
                .createdDate(LocalDateTime.now())
                .type(InformationType.EMAIL)
                .build();
        informationRepository.save(information);
        log.info("Send Email :{}",user.getEmail());
    }

    @KafkaListener(topics = "order-topic",groupId = "user-information-consumer-sms")
    private void sendSmsUserAboutOrder(String order) throws JsonProcessingException {
        JsonNode parser = StringToJsonConverter.stringToJsonConverter(order);
        log.info("Created Order: {}",parser);
        User user = userRepository.findById(parser.path("userId").asLong()).get();
        Information information = Information.builder()
                .user(user)
                .orderId(parser.path("id").asLong())
                .createdDate(LocalDateTime.now())
                .type(InformationType.SMS)
                .build();
        informationRepository.save(information);
        log.info("Send SMS :{}",user.getEmail());
    }
}
