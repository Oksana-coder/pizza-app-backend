package com.example.pizzaApp.exceptions;

public class PizzaNotFoundException extends RuntimeException {
    public PizzaNotFoundException(Long id) {
        super("Pizza with id " + id + " does not exist.");
    }
}
