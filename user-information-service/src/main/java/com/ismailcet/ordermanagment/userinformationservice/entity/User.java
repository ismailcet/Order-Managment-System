package com.ismailcet.ordermanagment.userinformationservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @OneToMany(mappedBy = "user")
    private List<Information> informationList;
}
