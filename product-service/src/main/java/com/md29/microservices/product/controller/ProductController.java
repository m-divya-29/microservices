package com.md29.microservices.product.controller;

import com.md29.microservices.product.dto.ProductRequestRecordDTO;
import com.md29.microservices.product.dto.ProductResponseRecordDTO;
import com.md29.microservices.product.model.Product;
import com.md29.microservices.product.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {
    // @Autowired - not required as @RequiredArgsConstructor takes care of it
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseRecordDTO createProduct(@RequestBody ProductRequestRecordDTO requestRecordDTO) {
        return productService.createProduct(requestRecordDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseRecordDTO getProduct(@RequestParam String id){
        return productService.getProduct(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseRecordDTO> getAllProducts(){
        return productService.getAllProducts();
    }
}
