package com.ismailcet.ordermanagment.orderservice.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "productId")
    private Long productId;
    @Column(name = "userId")
    private Long userId;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @Column(name = "cargoStatus")
    private String cargoStatus;

}
