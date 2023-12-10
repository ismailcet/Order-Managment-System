package com.ismailcet.ordermanagment.inventoryservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Order {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("productId")
    private Long productId;
    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("status")
    private String status;
    @JsonProperty("cargoStatus")
    private String cargoStatus;
}
