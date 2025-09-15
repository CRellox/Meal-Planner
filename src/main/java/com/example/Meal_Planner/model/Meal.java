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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Meal extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Η ονομασία είναι υποχρεωτική.")
    private String name;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    private String ingredients;

    private Integer prepTime;

    public Meal(Object id, @NotNull(message = "Το όνομα δεν μπορεί να είναι null.")
                           @Size(min = 2)
                           String name,

                           @NotNull(message = "Τα συστατικά δεν μπορούν να είναι null.")
                           @Size(min = 2)
                           String ingredients,

                           String prepTime,

                           @NotNull(message = "Ο τύπος δεν μπορεί να είναι null.")
                           MealType mealType) {
    }
}

