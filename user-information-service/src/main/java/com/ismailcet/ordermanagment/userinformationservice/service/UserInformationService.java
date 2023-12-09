package com.ismailcet.ordermanagment.userinformationservice.service;

import com.ismailcet.ordermanagment.userinformationservice.controller.request.CreateUserRequest;
import com.ismailcet.ordermanagment.userinformationservice.dto.UserDTO;
import com.ismailcet.ordermanagment.userinformationservice.entity.User;
import com.ismailcet.ordermanagment.userinformationservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInformationService {
    private final UserRepository userRepository;

    public UserInformationService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
