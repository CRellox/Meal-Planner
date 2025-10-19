package com.example.Meal_Planner.dto;

import com.example.Meal_Planner.core.enums.MealType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MealReadOnlyDTO {
    private String name;
    private String ingredients;
    private Long prepTime;
    private Long id;
    private MealType mealType;
}
