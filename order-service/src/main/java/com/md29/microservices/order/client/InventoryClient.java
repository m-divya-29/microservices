package com.md29.microservices.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "inventory", url = "http://localhost:8082")
public interface InventoryClient {
    @GetMapping(value = "/api/inventory")
    public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);
}
