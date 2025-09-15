package com.example.Meal_Planner.repository;


import com.example.Meal_Planner.core.enums.MealType;
import com.example.Meal_Planner.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {

    List<Meal> findByMealType(MealType mealType);
}