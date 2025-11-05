package com.example.Meal_Planner.service;

import com.example.Meal_Planner.core.exceptions.EntityAlreadyExistsException;
import com.example.Meal_Planner.core.exceptions.EntityInvalidArgumentException;
import com.example.Meal_Planner.core.exceptions.EntityNotFoundException;
import com.example.Meal_Planner.dto.MealEditDTO;
import com.example.Meal_Planner.dto.MealInsertDTO;
import com.example.Meal_Planner.model.Meal;

public interface IMealService {

    Meal saveMeal(MealInsertDTO mealInsertDTO) throws EntityAlreadyExistsException;

    void updateMeal(MealEditDTO mealEditDTO) throws EntityAlreadyExistsException, EntityNotFoundException;

    void deleteMealByUUID(String uuid) throws EntityNotFoundException;
}
