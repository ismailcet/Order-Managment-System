package com.ismailcet.ordermanagment.userinformationservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Table(name = "information")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private User user;
    @Column(name = "type")
    private String type;
    @Column(name = "createdDate")
    private Date createdDate;
}
