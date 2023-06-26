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
			new User("Harry", "harry@gmail.com", bCryptPasswordEncoder().encode("12345"), Role.USER),
			new User("Ron", "ron@gmail.com", bCryptPasswordEncoder().encode("09876"), Role.USER),
			new User("Hermione", "hermione@gmail.com", bCryptPasswordEncoder().encode("qwerty"), Role.USER),
			new User("Neville", "neville@gmail.com", bCryptPasswordEncoder().encode("poiuy"), Role.USER),
		};

		for (int i = 0; i < users.length; i++) {
			userRepository.save(users[i]);
		}

		Pizza[] pizzas = new Pizza[] {
				new Pizza("Diavolo", 13, "https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/36DFE80A-776A-49F7-B4BB-273C0A702BEE/Derivates/b6c3bc06-581e-4da9-a38f-8b6d47d62891.jpg"),
				new Pizza("Quattro Stagioni", 16, "https://www.italianstylecooking.net/wp-content/uploads/2022/01/Pizza-quattro-stagioni-1200x900.jpg"),
				new Pizza("Margherita", 12, "https://cdn.shopify.com/s/files/1/0410/4598/3397/articles/Margherita-9920.jpg?v=1644589966"),
				new Pizza("Quattro Formaggi", 14, "https://www.tasteatlas.com/images/recipes/946f07b7b5ec4f23a6fea58a2ac72650.jpg?mw=910"),
				new Pizza("Buffala", 16, "https://www.kuechengoetter.de/uploads/media/630x630/04/42534-pizza-margherita.jpg?v=1-0"),
				new Pizza("Salami", 12, "https://image.stern.de/5963252/t/5F/v7/w1440/r1/-/salamipizza-jpg--5523cef8c0641645-.jpg"),
				new Pizza("Prosciutto e Funghi", 12, "https://i1.wp.com/www.piccolericette.net/piccolericette/wp-content/uploads/2019/10/4102_Pizza.jpg?fit=1216%2C616&ssl=1"),
				new Pizza("Frutti di Mare", 12, "https://la-palma-neuwied.com/pizzaria/wp-content/uploads/2016/08/shutterstock_290954402.jpg"),
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
					.allowedOrigins("*")
					.allowedMethods("*");
		}
	};
	}
}
