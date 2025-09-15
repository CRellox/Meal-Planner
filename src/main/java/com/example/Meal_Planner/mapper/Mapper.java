package com.example.Meal_Planner.mapper;

import com.example.Meal_Planner.dto.MealEditDTO;
import com.example.Meal_Planner.dto.MealInsertDTO;
import com.example.Meal_Planner.dto.MealReadOnlyDTO;
import com.example.Meal_Planner.model.Meal;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Meal mapToMealEntity(MealInsertDTO dto) {
        return new Meal(null, dto.getName(), dto.getIngredients(), dto.getPrepTime(), dto.getMealType());
    }

    public MealReadOnlyDTO mapToMealReadOnlyDTO(Meal meal) {
        return new MealReadOnlyDTO(meal.getCreatedAt(), meal.getUpdatedAt(), meal.getName(), meal.getIngredients(),
                   meal.getId(), meal.getMealType(), meal.getPrepTime());
    }

    public MealEditDTO mapToMealEditDTO(Meal meal) {
        return new MealEditDTO(meal.getName(), meal.getIngredients(),
                               meal.getMealType(), meal.getPrepTime());
    }
}
