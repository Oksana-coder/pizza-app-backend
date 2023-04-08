package com.example.pizzaApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @NonNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Min(value = 0, message = "Price cannot be negative")
    @NonNull
    @Column(name = "price", nullable = false)
    private float price;

    @JsonIgnore
    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL)
    private List<Order> orders;
}
