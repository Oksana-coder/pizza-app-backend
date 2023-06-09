package com.example.pizzaApp.controllers;

import com.example.pizzaApp.models.Pizza;
import com.example.pizzaApp.services.PizzaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    @GetMapping
    public ResponseEntity<List<Pizza>> getAllPizzas() {
        return new ResponseEntity<>(pizzaService.getAllPizzas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Pizza> getPizza(@PathVariable Long id) {
        return new ResponseEntity<>(pizzaService.getPizza(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pizza> savePizza(@Valid @RequestBody Pizza pizza) {
        return new ResponseEntity<>(pizzaService.savePizza(pizza), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePizza(@PathVariable Long id) {
        pizzaService.deletePizza(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
