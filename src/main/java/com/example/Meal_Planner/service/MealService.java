package com.example.Meal_Planner.service;

import com.example.Meal_Planner.core.enums.MealType;
import com.example.Meal_Planner.mapper.Mapper;
import com.example.Meal_Planner.model.Meal;
import com.example.Meal_Planner.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MealService {

    private final MealRepository mealRepository;
    private final Mapper mapper;

    public List <Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    public List <Meal> findMealByType(MealType mealType) {
        return mealRepository.findByMealType(mealType);
    }

    public void findById(Long id) {
        mealRepository.findById(id);
    }

    public List <Meal> findByIngredients(String ingredients) {
        return mealRepository.findByIngredients(ingredients);
    }

    public Meal saveMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }
}
