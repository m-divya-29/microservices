package com.md29.microservices.product.service;

import com.md29.microservices.product.dto.ProductRequestRecordDTO;
import com.md29.microservices.product.dto.ProductResponseRecordDTO;
import com.md29.microservices.product.model.Product;
import com.md29.microservices.product.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductService {
    // @Autowired
    private final IProductRepository productRepository;

    public ProductResponseRecordDTO createProduct(ProductRequestRecordDTO productRequestRecordDTO) {
        Product product = Product.builder()
                .name(productRequestRecordDTO.name())
                .description(productRequestRecordDTO.description())
                .price(productRequestRecordDTO.price())
                .build();

        productRepository.save(product);
        log.info("Product saved");
        return new ProductResponseRecordDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    public ProductResponseRecordDTO getProduct(String id) {
        ProductResponseRecordDTO responseRecordDTO = null;
        Optional<Product> productOptional =  productRepository.findById(id);
        Product product = productOptional.get();
        if(product != null) {
            responseRecordDTO = new ProductResponseRecordDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice());
        }
        return responseRecordDTO;
    }

    public List<ProductResponseRecordDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponseRecordDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice())).collect(Collectors.toList());
    }
}
