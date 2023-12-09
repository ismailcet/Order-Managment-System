package com.ismailcet.ordermanagment.userinformationservice.controller.request;

import com.ismailcet.ordermanagment.userinformationservice.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    private String name;
    private String email;
    private String phoneNumber;
}
