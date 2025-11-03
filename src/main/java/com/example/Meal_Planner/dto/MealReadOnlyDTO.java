package com.example.Meal_Planner.dto;

import com.example.Meal_Planner.core.enums.MealType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MealReadOnlyDTO {
    private String name;
    private String ingredients;
    private String prepTime;
    private String instructions;
    private String uuid;
    private Long id;
    private MealType mealType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
