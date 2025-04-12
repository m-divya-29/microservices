package com.md29.microservices.product.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
@Document(value = "product")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private String skuCode;
    private BigDecimal price;

}
