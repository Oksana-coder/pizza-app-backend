package com.example.pizzaApp;

import com.example.pizzaApp.exceptions.ErrorResponse;
import com.example.pizzaApp.exceptions.OrderNotFoundException;
import com.example.pizzaApp.exceptions.PizzaNotFoundException;
import com.example.pizzaApp.exceptions.UserNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler({OrderNotFoundException.class, PizzaNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException exception) {
        ErrorResponse error = new ErrorResponse((Arrays.asList(exception.getMessage())));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        ErrorResponse error = new ErrorResponse((Arrays.asList("Data Integrity Violation: we cannot process your request.")));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> errors.add(error.getDefaultMessage()));
        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }
}

