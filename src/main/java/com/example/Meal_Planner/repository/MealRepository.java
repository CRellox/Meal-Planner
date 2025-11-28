package com.example.Meal_Planner.repository;

import java.util.List;
import com.example.Meal_Planner.core.enums.MealType;
import com.example.Meal_Planner.model.Meal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MealRepository extends JpaRepository<Meal, Long>, JpaSpecificationExecutor<Meal> {

    Optional<Meal> findByUuid(String uuid);
    Optional<Meal> findByName(String name);
    List<Meal> findByMealType(MealType mealType);
    Page<Meal> findByMealType(MealType mealType, Pageable pageable);
}