package com.example.pizzaApp;

import com.example.pizzaApp.models.Pizza;
import com.example.pizzaApp.models.User;
import com.example.pizzaApp.repositories.PizzaRepository;
import com.example.pizzaApp.repositories.UserRepository;
import com.example.pizzaApp.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
			new User("Harry", "harry@gmail.com", "12345", Role.USER),
			new User("Ron", "ron@gmail.com", "09876", Role.USER),
			new User("Hermione", "hermione@gmail.com", "qwerty", Role.USER),
			new User("Neville", "neville@gmail.com", "poiuy", Role.USER),
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

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
	return new WebMvcConfigurer() {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
					.allowedOrigins("http://127.0.0.1:5173")
					.allowedMethods("*");
		}
	};
	}
}
