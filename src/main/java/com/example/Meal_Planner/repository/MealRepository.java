package com.example.Meal_Planner.repository;


import com.example.Meal_Planner.core.enums.MealType;
import com.example.Meal_Planner.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    List<Meal> findByMealType(MealType mealType);
    List<Meal> findByIngredients(String ingredients);
}