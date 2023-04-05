package com.example.pizzaApp.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("The user with id " + id + " does not exist.");
    }
}
