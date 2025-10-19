package com.example.Meal_Planner.mapper;

import com.example.Meal_Planner.dto.MealEditDTO;
import com.example.Meal_Planner.dto.MealInsertDTO;
import com.example.Meal_Planner.dto.MealReadOnlyDTO;
import com.example.Meal_Planner.model.Meal;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Meal mapToMealEntity(MealInsertDTO dto) {
        return new Meal(dto.getId(), dto.getName(), dto.getMealType(),dto.getIngredients(), dto.getPrepTime());
    }

    public MealReadOnlyDTO mapToMealReadOnlyDTO(Meal meal) {
        return new MealReadOnlyDTO(meal.getName(), meal.getIngredients(),
                meal.getPrepTime(), meal.getId(), meal.getMealType());
    }

    public MealEditDTO mapToMealEditDTO(Meal meal) {
        return new MealEditDTO(meal.getName(), meal.getIngredients(),
                meal.getPrepTime(), meal.getMealType(), meal.getId());
    }
}
