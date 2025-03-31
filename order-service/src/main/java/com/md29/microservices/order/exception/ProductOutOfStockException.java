package com.md29.microservices.order.exception;

public class ProductOutOfStockException extends Exception{
    public  ProductOutOfStockException(String message) {
        super(message);
    }
}
