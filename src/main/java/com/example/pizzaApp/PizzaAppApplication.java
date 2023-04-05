package com.example.pizzaApp;

import com.example.pizzaApp.models.User;
import com.example.pizzaApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzaAppApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(PizzaAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User[] users = new User[] {
			new User("Harry", "harry@gmail.com"),
			new User("Ron", "ron@gmail.com"),
			new User("Hermione", "hermione@gmail.com"),
			new User("Neville", "neville@gmail.com"),
		};

		for (int i = 0; i < users.length; i++) {
			userRepository.save(users[i]);
		}
	}
}
