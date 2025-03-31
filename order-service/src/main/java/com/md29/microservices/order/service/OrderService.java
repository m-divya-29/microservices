package com.md29.microservices.order.service;

import com.md29.microservices.order.client.InventoryClient;
import com.md29.microservices.order.dto.OrderRequest;
import com.md29.microservices.order.exception.ProductOutOfStockException;
import com.md29.microservices.order.model.Order;
import com.md29.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    @Autowired
    public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
    }
    public void placeOrder(OrderRequest orderRequest) throws ProductOutOfStockException {
        boolean inStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if(!inStock) {
            throw new ProductOutOfStockException("Product with sku code: " + orderRequest.skuCode() + " is not in stock.");
        }
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price());
        order.setSkuCode(orderRequest.skuCode());
        order.setQuantity(orderRequest.quantity());

        orderRepository.save(order);
    }
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

}
