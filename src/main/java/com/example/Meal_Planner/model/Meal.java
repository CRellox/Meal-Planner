package com.example.Meal_Planner.model;


import com.example.Meal_Planner.core.enums.MealType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "meals")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2)
    @NotBlank(message = "Η ονομασία είναι υποχρεωτική.")
    private String name;

    @NotNull(message = "Ο τύπος δεν μπορεί να είναι null.")
    @Enumerated(EnumType.STRING)
    private MealType mealType;

    @NotNull(message = "Τα συστατικά δεν μπορούν να είναι null.")
    @Size(min = 2)
    private String ingredients;

    private Long prepTime;

    @NotNull
    private String instructions;
}

