package com.example.pizzaApp.services;

import com.example.pizzaApp.exceptions.OrderNotFoundException;
import com.example.pizzaApp.models.Order;
import com.example.pizzaApp.models.User;
import com.example.pizzaApp.repositories.OrderRepository;
import com.example.pizzaApp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return unwrapOrder(order, id);
    }

    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order saveOrder(Order order, Long userId) {
        User user = UserService.unwrapUser(userRepository.findById(userId), userId);
        order.setUser(user);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    static Order unwrapOrder(Optional<Order> entity, Long id) {
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new OrderNotFoundException(id);
        }
    }
}
