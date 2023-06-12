package com.example.pizzaApp;

import com.example.pizzaApp.models.Pizza;
import com.example.pizzaApp.models.User;
import com.example.pizzaApp.repositories.PizzaRepository;
import com.example.pizzaApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzaAppApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PizzaRepository pizzaRepository;

	public static void main(String[] args) {
		SpringApplication.run(PizzaAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User[] users = new User[] {
			new User("Harry", "harry@gmail.com", "12345"),
			new User("Ron", "ron@gmail.com", "09876"),
			new User("Hermione", "hermione@gmail.com", "qwerty"),
			new User("Neville", "neville@gmail.com", "poiuy"),
		};

		for (int i = 0; i < users.length; i++) {
			userRepository.save(users[i]);
		}

		Pizza[] pizzas = new Pizza[] {
				new Pizza("Diavolo", 13),
				new Pizza("Quattro Stagioni", 16),
				new Pizza("Margherita", 12),
				new Pizza("Quattro Formaggi", 12),
		};

		for (int i = 0; i < pizzas.length; i++) {
			pizzaRepository.save(pizzas[i]);
		}
	}
}
