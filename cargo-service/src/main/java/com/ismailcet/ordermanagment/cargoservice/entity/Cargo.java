package com.ismailcet.ordermanagment.cargoservice.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cargo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "orderId")
    private Long orderId;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CargoStatus status;
    @Column(name = "createdDate")
    private LocalDateTime createdDate;
    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;
    @Column(name = "expectedDate")
    private LocalDateTime expectedDate;
}
