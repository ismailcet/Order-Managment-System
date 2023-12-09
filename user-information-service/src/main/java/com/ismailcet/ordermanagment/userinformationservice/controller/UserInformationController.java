package com.ismailcet.ordermanagment.userinformationservice.controller;

import com.ismailcet.ordermanagment.userinformationservice.controller.request.CreateUserRequest;
import com.ismailcet.ordermanagment.userinformationservice.controller.response.CheckUserResponse;
import com.ismailcet.ordermanagment.userinformationservice.dto.UserDTO;
import com.ismailcet.ordermanagment.userinformationservice.service.UserInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/userinformation")
public class UserInformationController {
    private final UserInformationService userInformationService;

    public UserInformationController(UserInformationService userInformationService) {
        this.userInformationService = userInformationService;
    }

    @PostMapping(path = "/createUser")
    private ResponseEntity<UserDTO> createUser(@RequestBody CreateUserRequest request){
        UserDTO response = userInformationService.createUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(path = "/checkUser")
    private ResponseEntity<CheckUserResponse> checkUser(@RequestBody Long userId){
        CheckUserResponse response = new CheckUserResponse();
        response.setResult(userInformationService.checkUser(userId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
