package com.example.Meal_Planner.model;


import com.example.Meal_Planner.core.enums.MealType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Η ονομασία είναι υποχρεωτική.")
    private String name;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    private String ingredients;

    private Integer prepTime;
}

