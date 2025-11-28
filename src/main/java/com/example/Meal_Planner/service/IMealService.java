package com.example.Meal_Planner.service;

import com.example.Meal_Planner.core.enums.MealType;
import com.example.Meal_Planner.core.exceptions.EntityAlreadyExistsException;
import com.example.Meal_Planner.core.exceptions.EntityNotFoundException;
import com.example.Meal_Planner.dto.MealEditDTO;
import com.example.Meal_Planner.dto.MealInsertDTO;
import com.example.Meal_Planner.dto.MealReadOnlyDTO;
import com.example.Meal_Planner.model.Meal;
import org.springframework.data.domain.Page;

public interface IMealService {

    Meal saveMeal(MealInsertDTO mealInsertDTO) throws EntityAlreadyExistsException;

    void updateMeal(MealEditDTO mealEditDTO) throws EntityAlreadyExistsException, EntityNotFoundException;

    void deleteMealByUUID(String uuid) throws EntityNotFoundException;

    Page<MealReadOnlyDTO> getPaginatedMeals(int page, int size);

    Page<MealReadOnlyDTO> getPaginatedMealsByType(MealType mealType, int page, int size);
}
