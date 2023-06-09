package com.example.pizzaApp.services;

import com.example.pizzaApp.exceptions.UserNotFoundException;
import com.example.pizzaApp.models.User;
import com.example.pizzaApp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return unwrapUser(user, id);
    }

    public User getUser(String name) {
        Optional<User> user = userRepository.findByName(name);
        return unwrapUser(user, 404L);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    static User unwrapUser(Optional<User> entity, Long id) {
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new UserNotFoundException(id);
        }
    }

}
