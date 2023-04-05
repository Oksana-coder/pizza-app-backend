package com.example.pizzaApp.repositories;

import com.example.pizzaApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
