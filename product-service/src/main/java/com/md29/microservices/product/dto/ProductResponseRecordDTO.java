package com.md29.microservices.product.dto;

import java.math.BigDecimal;

public record ProductResponseRecordDTO(String id, String name, String description, BigDecimal price) {
}
