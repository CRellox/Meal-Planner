package com.example.Meal_Planner.dto;

import com.example.Meal_Planner.core.enums.MealType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MealReadOnlyDTO {

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String name;
    private String ingredients;
    private String prepTime;
    private Long id;
    private MealType mealType;

    public MealReadOnlyDTO(LocalDateTime createdAt, LocalDateTime updatedAt,
                           @NotBlank(message = "Η ονομασία είναι υποχρεωτική.") String name,
                           String ingredients, Long id, MealType mealType, Integer prepTime) {
    }
}
