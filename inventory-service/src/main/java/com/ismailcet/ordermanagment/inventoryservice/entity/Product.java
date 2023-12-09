package com.ismailcet.ordermanagment.inventoryservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String productName;
    @Column(name = "amount")
    private Integer amount;
}
