package com.example.Meal_Planner.service;

import com.example.Meal_Planner.dto.MealEditDTO;
import com.example.Meal_Planner.dto.MealInsertDTO;
import com.example.Meal_Planner.model.Meal;

public interface IMealService {

    Meal saveMeal(MealInsertDTO mealInsertDTO);

    void updateMeal(MealEditDTO mealEditDTO);

    void deleteMealById(Long id);

    Meal createMeal(MealInsertDTO mealInsertDTO);
}
