package com.example.Meal_Planner.service;

import com.example.Meal_Planner.dto.MealEditDTO;
import com.example.Meal_Planner.dto.MealInsertDTO;
import com.example.Meal_Planner.mapper.Mapper;
import com.example.Meal_Planner.model.Meal;
import com.example.Meal_Planner.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class MealService implements IMealService {

    private final MealRepository mealRepository;
    private final Mapper mapper;

    @Override
    public Meal saveMeal(MealInsertDTO dto) {
        Meal meal = mapper.mapToMealEntity(dto);
        mealRepository.save(meal);
        log.info("Meal with id={} saved.", dto.getId());
        return meal;
    }

    @Override
    public void updateMeal(MealEditDTO dto) {
    }

    @Override
    public void deleteMealById(Long id) {
    }

    @Override
    public Meal createMeal(MealInsertDTO dto) {
        Meal meal = mapper.mapToMealEntity(dto);
        mealRepository.save(meal);
        return meal;
    }


//    public List <Meal> getAllMeals() {
//        return mealRepository.findAll();
//    }
//
//    public List <Meal> findMealByType(MealType mealType) {
//        return mealRepository.findByMealType(mealType);
//    }
//
//    public List <Meal> findByIngredients(String ingredients) {
//        return mealRepository.findByIngredients(ingredients);
//    }
}
