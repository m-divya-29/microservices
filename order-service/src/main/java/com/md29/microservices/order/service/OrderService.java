package com.md29.microservices.order.service;

import com.md29.microservices.order.client.InventoryClient;
import com.md29.microservices.order.dto.OrderRequest;
import com.md29.microservices.order.events.OrderPlacedEvent;
import com.md29.microservices.order.exception.ProductOutOfStockException;
import com.md29.microservices.order.model.Order;
import com.md29.microservices.order.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
    Logger log = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient, KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
        this.kafkaTemplate = kafkaTemplate;
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
        // Send message to Kafka topic
        OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(order.getOrderNumber(), "testemail@gmail.com");
        log.info("START ---- sending OrderPlacedEvent{} to Kafka Topic order-placed", orderPlacedEvent);
        kafkaTemplate.send("order-placed", orderPlacedEvent);
        log.info("END ---- sending OrderPlacedEvent{} to Kafka Topic order-placed", orderPlacedEvent);


    }
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

}
