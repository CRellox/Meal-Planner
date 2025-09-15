package com.example.Meal_Planner.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MealInsertDTO {

    @NotNull(message = "Το όνομα δεν μπορεί να είναι null.")
    private String name;

    @NotNull(message = "Τα συστατικά δεν μπορούν να είναι null.")
    private String ingredients;

    private String prepTime;

    @NotNull(message = "Ο τύπος δεν μπορεί να είναι null.")
    private String mealType;
}
