package com.example.Meal_Planner.service;

import com.example.Meal_Planner.core.enums.MealType;
import com.example.Meal_Planner.core.exceptions.EntityAlreadyExistsException;
import com.example.Meal_Planner.core.exceptions.EntityNotFoundException;
import com.example.Meal_Planner.dto.MealEditDTO;
import com.example.Meal_Planner.dto.MealInsertDTO;
import com.example.Meal_Planner.dto.MealReadOnlyDTO;
import com.example.Meal_Planner.model.Meal;
import com.example.Meal_Planner.model.User;
import org.springframework.data.domain.Page;

public interface IMealService {

    Meal saveMeal(MealInsertDTO mealInsertDTO, User user) throws EntityAlreadyExistsException;

    void updateMeal(MealEditDTO mealEditDTO, User user) throws EntityAlreadyExistsException, EntityNotFoundException;

    void deleteMealByUUID(String uuid, User user) throws EntityNotFoundException;

    Meal getMealByUuid(String uuid, User user) throws  EntityNotFoundException;

    long getFavoriteMealsCount();

    void toggleFavoriteMeal(String uuid, User user) throws EntityNotFoundException;

    Page<MealReadOnlyDTO> getPaginatedMeals(int page, int size);

    Page<MealReadOnlyDTO> getPaginatedMealsByType(MealType mealType, int page, int size);

    Page<MealReadOnlyDTO> getPaginatedFavoriteMeals(int page, int size);

    Page<MealReadOnlyDTO> getPaginatedFavoriteMealsByType(MealType mealType, int page, int size);
}
