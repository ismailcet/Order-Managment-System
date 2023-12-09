package com.ismailcet.ordermanagment.inventoryservice.service;

import com.ismailcet.ordermanagment.inventoryservice.controller.request.CheckProductRequest;
import com.ismailcet.ordermanagment.inventoryservice.controller.request.CreateProductRequest;
import com.ismailcet.ordermanagment.inventoryservice.dto.ProductDTO;
import com.ismailcet.ordermanagment.inventoryservice.entity.Product;
import com.ismailcet.ordermanagment.inventoryservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO createProduct(CreateProductRequest request) {
        Optional<Product> productControl =
                productRepository.findByProductName(request.getName());
        if(!productControl.isPresent()){
            Product product = Product.builder()
                    .productName(request.getName())
                    .amount(request.getAmount())
                    .build();
            productRepository.save(product);
            return new ProductDTO(product);
        }else{
            throw new NullPointerException("Eklemek istediginiz Product Mevcuttur ! ");
        }
    }

    public Boolean checkProductControl(CheckProductRequest request) {
        Optional<Product> product = productRepository.findById(request.getProductId());
        if(product.isPresent()){
            return true;
        }else{
            return false;
        }
    }
}
