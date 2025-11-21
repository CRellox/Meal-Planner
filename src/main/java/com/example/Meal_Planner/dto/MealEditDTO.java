package com.example.Meal_Planner.dto;

import com.example.Meal_Planner.core.enums.MealType;
import jakarta.validation.constraints.Min;
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
    private MealType mealType;

    @NotNull
    private Long Id;

    @NotNull
    private String uuid;

    @NotNull
    @Size(min = 2, message = "Name must have at least 2 characters")
    private String name;

    @NotNull
    @Size(min = 2, message = "Minimum 1 ingredient")
    private String ingredients;

    @NotNull
    @Min(value = 1, message = "Minimum 1 minuet")
    private Integer prepTime;

    @NotNull
    @Size(min = 10, message = "Instructions must have at least 10 characters")
    private String instructions;
}
