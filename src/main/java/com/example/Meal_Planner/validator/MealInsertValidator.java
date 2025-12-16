package com.example.Meal_Planner.validator;

import com.example.Meal_Planner.dto.MealInsertDTO;
import com.example.Meal_Planner.model.Meal;
import com.example.Meal_Planner.model.User;
import com.example.Meal_Planner.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class MealInsertValidator {

    private final MealRepository mealRepository;

    public void validate(MealInsertDTO dto, Errors errors, User user) {

        if (dto.getName() == null || dto.getName().isBlank()) {
            return;
        }

        Optional<Meal> existingMeal = mealRepository.findByNameAndUser(dto.getName(), user);

        if (existingMeal.isPresent()) {
            log.error(
                    "Meal name={} already exists for user={}",
                    dto.getName(),
                    user.getUsername()
            );
            errors.rejectValue("name", "meal.name.exists");
        }
    }
}