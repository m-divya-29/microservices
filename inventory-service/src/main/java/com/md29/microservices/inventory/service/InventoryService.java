package com.md29.microservices.inventory.service;

import com.md29.microservices.inventory.model.Inventory;
import com.md29.microservices.inventory.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InventoryService {
    private final InventoryRepository repository;

    public List<Inventory> getAll() {
        return repository.findAll();
    }

    /**
     * Check if inventory of skuCode exists with quantity >= required quantity.
     * @param skuCode code of the product
     * @param quantity required quantity
     * @return true/false
     */
    public boolean isInStock(String skuCode, Integer quantity){
        return repository.existsBySkuCodeAndQuantityGreaterThanEqual(skuCode, quantity);
    }

}
