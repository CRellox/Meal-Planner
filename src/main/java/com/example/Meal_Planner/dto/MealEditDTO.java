package com.example.Meal_Planner.dto;

import com.example.Meal_Planner.core.enums.MealType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MealEditDTO {

    @NotNull(message = "Το όνομα δεν μπορεί να είναι null.")
    @Size(min = 2)
    private String name;

    @NotNull(message = "Τα συστατικά δεν μπορούν να είναι null.")
    @Size(min = 2)
    private String ingredients;

    private String prepTime;

    @NotNull(message = "Ο τύπος δεν μπορεί να είναι null.")
    private MealType mealType;
}
