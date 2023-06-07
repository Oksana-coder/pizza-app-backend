package com.example.pizzaApp.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("Order with id " + id + " does not exist.");
    }
}
