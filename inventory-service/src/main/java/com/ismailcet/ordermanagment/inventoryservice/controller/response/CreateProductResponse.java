package com.ismailcet.ordermanagment.inventoryservice.controller.response;

import com.ismailcet.ordermanagment.inventoryservice.dto.ProductDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductResponse {
    private ProductDTO productDTO;
}
