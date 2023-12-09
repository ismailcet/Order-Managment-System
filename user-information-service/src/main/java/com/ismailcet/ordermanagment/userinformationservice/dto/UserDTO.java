package com.ismailcet.ordermanagment.userinformationservice.dto;

import com.ismailcet.ordermanagment.userinformationservice.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;

    public UserDTO(User user){
        this.id = user.getId();
        this.name = user.getEmail();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
    }
}
