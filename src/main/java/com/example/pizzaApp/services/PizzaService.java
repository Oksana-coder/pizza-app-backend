package com.example.pizzaApp.services;

import com.example.pizzaApp.exceptions.PizzaNotFoundException;
import com.example.pizzaApp.models.Pizza;
import com.example.pizzaApp.repositories.PizzaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    public Pizza getPizza(Long id) {
        Optional<Pizza> pizza = pizzaRepository.findById(id);
        return unwrapPizza(pizza, id);
    }

    public Pizza savePizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public void deletePizza(Long id) {
        pizzaRepository.deleteById(id);
    }

    static Pizza unwrapPizza(Optional<Pizza> entity, Long id) {
        if(entity.isPresent()) {
            return entity.get();
        } else {
            throw new PizzaNotFoundException(id);
        }
    }
}
