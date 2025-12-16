package com.example.Meal_Planner.mapper;

import com.example.Meal_Planner.dto.MealEditDTO;
import com.example.Meal_Planner.dto.MealInsertDTO;
import com.example.Meal_Planner.dto.MealReadOnlyDTO;
import com.example.Meal_Planner.dto.UserInsertDTO;
import com.example.Meal_Planner.model.Meal;
import com.example.Meal_Planner.model.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Meal mapToMealEntity(MealInsertDTO dto) {
        Meal meal = new Meal();

        meal.setMealType(dto.getMealType());
        meal.setName(dto.getName());
        meal.setIngredients(dto.getIngredients());
        meal.setPrepTime(dto.getPrepTime());
        meal.setInstructions(dto.getInstructions());
        meal.setFavorite(dto.isFavorite());

        return meal;
    }

    public MealReadOnlyDTO mapToMealReadOnlyDTO(Meal meal) {
        return new MealReadOnlyDTO(
                meal.getName(),
                meal.getIngredients(),
                meal.getPrepTime(),
                meal.getInstructions(),
                meal.getUuid(),
                meal.getId(),
                meal.getMealType(),
                meal.getCreatedAt(),
                meal.getUpdatedAt(),
                meal.isFavorite());
    }

    public MealEditDTO mapToMealEditDTO(Meal meal) {
        return new MealEditDTO(
                meal.getUuid(),
                meal.getMealType(),
                meal.getName(),
                meal.getIngredients(),
                meal.getPrepTime(),
                meal.getInstructions(),
                meal.isFavorite(),
                meal.getUser());
    }

    public User mapToUserEntity(UserInsertDTO userInsertDTO) {
        return User.builder()
                .username(userInsertDTO.getUsername())
                .password(userInsertDTO.getPassword())
                .email(userInsertDTO.getEmail())
                .build();
    }
}