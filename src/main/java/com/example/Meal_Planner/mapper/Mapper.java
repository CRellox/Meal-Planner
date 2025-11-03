package com.example.Meal_Planner.mapper;

import com.example.Meal_Planner.dto.MealEditDTO;
import com.example.Meal_Planner.dto.MealInsertDTO;
import com.example.Meal_Planner.dto.MealReadOnlyDTO;
import com.example.Meal_Planner.model.Meal;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Meal mapToMealEntity(MealInsertDTO dto) {
        return new Meal(null, null, dto.getMealType(), dto.getName(),
                dto.getIngredients(), dto.getPrepTime(), dto.getInstructions());
    }

    public MealReadOnlyDTO mapToMealReadOnlyDTO(Meal meal) {
        return new MealReadOnlyDTO(meal.getName(), meal.getIngredients(), meal.getPrepTime(),
                meal.getInstructions(), meal.getUuid(), meal.getId(), meal.getMealType(),
                meal.getCreatedAt(), meal.getUpdatedAt());
    }

    public MealEditDTO mapToMealEditDTO(Meal meal) {
        return new MealEditDTO(meal.getMealType(), meal.getId(), meal.getUuid(), meal.getName(), meal.getIngredients(),
                meal.getPrepTime(), meal.getInstructions());
    }
}
