package com.example.Meal_Planner.validator;

import com.example.Meal_Planner.dto.MealInsertDTO;
import com.example.Meal_Planner.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@Slf4j
@RequiredArgsConstructor
public class MealInsertValidator implements Validator {

    private final MealRepository mealRepository;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return MealInsertDTO.class == clazz;
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        MealInsertDTO mealInsertDTO =(MealInsertDTO) target;

        if (mealInsertDTO.getName() !=null && mealRepository.findByName(mealInsertDTO.getName()).isPresent()) {
            log.error("Save failed for meal with name={}. Meal already exists.", mealInsertDTO.getName());
            errors.rejectValue("name", "name.meal.exists");
        }
    }
}
