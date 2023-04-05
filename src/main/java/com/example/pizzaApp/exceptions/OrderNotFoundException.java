package com.example.pizzaApp.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("The order with id " + id + " does not exist.");
    }
}
