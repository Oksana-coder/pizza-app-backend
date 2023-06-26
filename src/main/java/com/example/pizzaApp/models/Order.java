package com.example.pizzaApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @Past(message = "The order date must be in the past")
    @NonNull
    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @Min(value = 0, message = "Total price cannot be negative")
    @NonNull
    @Column(name = "total_price", nullable = false)
    private float totalPrice;

    @NotBlank(message = "Description cannot be blank")
    @NonNull
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotEmpty(message = "Pizzas field cannot be blank")
//    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "order_pizza",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "id")
    )
    private List<Pizza> pizzas;
}
