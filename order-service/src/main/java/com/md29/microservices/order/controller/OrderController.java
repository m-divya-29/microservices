package com.md29.microservices.order.controller;

import com.md29.microservices.order.dto.OrderRequest;
import com.md29.microservices.order.exception.ProductOutOfStockException;
import com.md29.microservices.order.model.Order;
import com.md29.microservices.order.service.OrderService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    @NonNull
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) throws ProductOutOfStockException {
        orderService.placeOrder(orderRequest);
        return "Order placed successfully!";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getOrders(){
        return orderService.getOrders();
    }
}
