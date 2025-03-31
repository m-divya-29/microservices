package com.md29.microservices.order.controlleradvice;

import com.md29.microservices.order.exception.ProductOutOfStockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderControllerAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleOutOfStock() {
        return "An Illegal Argument was passed";
    }

    @ExceptionHandler(ProductOutOfStockException.class)
    public ResponseEntity<String> handleOutOfStockException(ProductOutOfStockException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Out of stock: " + ex.getMessage());
    }
}
