package com.example.Meal_Planner.repository;


import com.example.Meal_Planner.core.enums.MealType;
import com.example.Meal_Planner.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long>, JpaSpecificationExecutor<Meal> {

    List<Meal> findByMealType(MealType mealType);
    Optional<Meal> findByMealById(Long id);
}