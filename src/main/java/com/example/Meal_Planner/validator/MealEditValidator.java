package com.example.Meal_Planner.validator;

import com.example.Meal_Planner.dto.MealEditDTO;
import com.example.Meal_Planner.model.Meal;
import com.example.Meal_Planner.repository.MealRepository;
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

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return MealEditDTO.class == clazz;
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        MealEditDTO mealEditDTO = (MealEditDTO) target;

        Meal meal = mealRepository.findByUuid(mealEditDTO.getUuid()).orElseThrow(null);

        if (meal !=null && meal.getUuid().equals(mealEditDTO.getUuid())) {
            if (mealRepository.findByName(mealEditDTO.getName()).isPresent()) {
                log.error("Save failed for meal with name={}. Meal already exists.", mealEditDTO.getName());
                errors.rejectValue("name", "name.meal.exists");
            }
        }
    }
}
