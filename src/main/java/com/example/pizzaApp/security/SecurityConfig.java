package com.example.pizzaApp.security;

import com.example.pizzaApp.repositories.UserRepository;
import com.example.pizzaApp.security.filter.AuthenticationFilter;
import com.example.pizzaApp.security.filter.ExceptionHandlerFilter;
import com.example.pizzaApp.security.filter.JWTAuthorizationFilter;
import com.example.pizzaApp.security.manager.CustomAuthenticationManager;
import com.example.pizzaApp.services.UserService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationManager customAuthenticationManager;
    private final UserService userService;
//    private final Gson gson;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager, userService);
        authenticationFilter.setFilterProcessesUrl("/users/authenticate");
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
                .addFilter(authenticationFilter)
                .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
}
