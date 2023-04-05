package com.example.pizzaApp.repositories;

import com.example.pizzaApp.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
