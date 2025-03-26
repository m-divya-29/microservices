package com.md29.microservices.product.dto;

import java.math.BigDecimal;

public record ProductRequestRecordDTO(String id, String name, String description, BigDecimal price) {
}
