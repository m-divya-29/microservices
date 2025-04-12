package com.md29.microservices.order.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface InventoryClient {
    @GetExchange(value = "/api/inventory")
    public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);
}
