package com.ismailcet.ordermanagment.inventoryservice.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequest {
    private String name;
    private Integer amount;
}
