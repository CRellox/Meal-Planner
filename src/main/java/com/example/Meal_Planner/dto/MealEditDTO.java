package com.example.Meal_Planner.dto;

import com.example.Meal_Planner.core.enums.MealType;
import com.example.Meal_Planner.model.User;
import jakarta.validation.constraints.Min;
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
public class MealEditDTO {

    @NotNull
    private String uuid;

    @NotNull
    private MealType mealType;

    @NotBlank       //(message = "Name is required")
    @Size(min = 2) //, message = "Name must have at least 2 characters")
    private String name;

    @NotBlank       //(message = "Ingredients are required")
    @Size(min = 2) //, message = "Minimum 1 ingredient is required.")
    private String ingredients;

    @NotNull         //(message = "Preparation time is required")
    @Min(value = 1) //, message = "Preparation time must be at least 1 minute")
    private Integer prepTime;

    @NotBlank        //(message = "Instructions are required")
    @Size(min = 10) //, message = "Instructions must have at least 10 characters")
    private String instructions;

    private boolean favorite;

    private User user;
}
