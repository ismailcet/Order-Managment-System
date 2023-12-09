package com.ismailcet.ordermanagment.inventoryservice.controller;

import com.ismailcet.ordermanagment.inventoryservice.controller.request.CheckProductRequest;
import com.ismailcet.ordermanagment.inventoryservice.controller.request.CreateProductRequest;
import com.ismailcet.ordermanagment.inventoryservice.controller.response.CheckProductResponse;
import com.ismailcet.ordermanagment.inventoryservice.controller.response.CreateProductResponse;
import com.ismailcet.ordermanagment.inventoryservice.dto.ProductDTO;
import com.ismailcet.ordermanagment.inventoryservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(path = "/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(path = "/createProduct")
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody CreateProductRequest request){
        CreateProductResponse response = new CreateProductResponse();
        ProductDTO productDTO = productService.createProduct(request);
        response.setProductDTO(productDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping(path = "checkProductId")
    public ResponseEntity<CheckProductResponse> checkProductControl(@RequestBody CheckProductRequest request){
        CheckProductResponse response = new CheckProductResponse();
        response.setResult(productService.checkProductControl(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
