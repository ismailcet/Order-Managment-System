package com.ismailcet.ordermanagment.userinformationservice.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @Column(name = "orderId")
    private Long orderId;
    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private User user;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private InformationType type;
    @Column(name = "createdDate")
    private LocalDateTime createdDate;
}
