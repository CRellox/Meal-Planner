package com.example.Meal_Planner.service;

import com.example.Meal_Planner.core.exceptions.EntityAlreadyExistsException;
import com.example.Meal_Planner.core.exceptions.EntityNotFoundException;
import com.example.Meal_Planner.dto.MealEditDTO;
import com.example.Meal_Planner.dto.MealInsertDTO;
import com.example.Meal_Planner.mapper.Mapper;
import com.example.Meal_Planner.model.Meal;
import com.example.Meal_Planner.repository.MealRepository;
import jakarta.transaction.Transactional;
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
    @Transactional(rollbackOn = { EntityAlreadyExistsException.class })
    public Meal saveMeal(MealInsertDTO dto) throws EntityAlreadyExistsException {
        try {
            if (dto.getName() != null && mealRepository.findByName(dto.getName()).isPresent()) {
                throw new EntityAlreadyExistsException
                        ("Meal", "This Meal name " + dto.getName() + " already exists");
            }

            Meal meal = mapper.mapToMealEntity(dto);
            mealRepository.save(meal);
            log.info("Meal with id={} saved.", dto.getId());
            return meal;
        } catch (EntityAlreadyExistsException e) {
            log.error("Save failed for Meal with name={}. Meal already exists", dto.getName(), e);
            throw e;
        }
    }

    @Override
    public void updateMeal(MealEditDTO dto) {
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteMealByUUID(String uuid) throws EntityNotFoundException {
        try {
            Meal meal = mealRepository.findByUuid(uuid)
                    .orElseThrow(() -> new EntityNotFoundException ("Meal", "Meal with uuid: " + uuid + " not found"));

            mealRepository.deleteById(meal.getId());
            log.info("Meal with uuid={} deleted.", uuid);
        } catch (EntityNotFoundException e) {
            log.error("Delete failed for Meal with uuid={}. Meal not found.", uuid, e);
            throw e;
        }
    }
}
