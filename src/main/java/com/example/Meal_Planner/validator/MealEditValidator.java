package com.example.Meal_Planner.validator;

import com.example.Meal_Planner.core.exceptions.EntityNotFoundException;
import com.example.Meal_Planner.dto.MealEditDTO;
import com.example.Meal_Planner.model.Meal;
import com.example.Meal_Planner.model.User;
import com.example.Meal_Planner.repository.MealRepository;
import com.example.Meal_Planner.service.IMealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@Slf4j
@RequiredArgsConstructor
public class MealEditValidator implements Validator {

    private final MealRepository mealRepository;
    private final IMealService mealService;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return MealEditDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MealEditDTO dto = (MealEditDTO) target;

        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            errors.rejectValue("name", "meal.name.empty", "The meal name cannot be empty.");
        }

        User user = dto.getUser();

        if (user != null && dto.getUuid() != null) {
            try {
                Meal meal = mealService.getMealByUuid(dto.getUuid(), user);
            } catch (EntityNotFoundException e) {
                errors.reject("meal.notFound", "Meal not found or does not exist.");
            }
        }
    }
}
