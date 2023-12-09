package com.ismailcet.ordermanagment.inventoryservice.dto;

import com.ismailcet.ordermanagment.inventoryservice.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private Integer amount;

    public ProductDTO(Product product){
        this.id = product.getId();
        this.name = product.getProductName();
        this.amount = product.getAmount();
    }
}
