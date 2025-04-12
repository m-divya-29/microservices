package com.md29.microservices.order.dto;

import java.math.BigDecimal;

public record OrderRequest(Long id,  String skuCode, BigDecimal price, Integer quantity) {
}
