package com.example.Meal_Planner.service;

import com.example.Meal_Planner.core.enums.MealType;
import com.example.Meal_Planner.core.exceptions.EntityAlreadyExistsException;
import com.example.Meal_Planner.core.exceptions.EntityNotFoundException;
import com.example.Meal_Planner.dto.MealEditDTO;
import com.example.Meal_Planner.dto.MealInsertDTO;
import com.example.Meal_Planner.dto.MealReadOnlyDTO;
import com.example.Meal_Planner.mapper.Mapper;
import com.example.Meal_Planner.model.Meal;
import com.example.Meal_Planner.repository.MealRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class MealService implements IMealService {

    private final MealRepository mealRepository;
    private final Mapper mapper;

    @Override
    @Transactional(rollbackOn = {EntityAlreadyExistsException.class})
    public Meal saveMeal(MealInsertDTO dto) throws EntityAlreadyExistsException {
        try {
            if (dto.getName() != null && mealRepository.findByName(dto.getName()).isPresent()) {
                throw new EntityAlreadyExistsException
                        ("Meal", "This Meal name " + dto.getName() + " already exists");
            }

            Meal meal = mapper.mapToMealEntity(dto);
            mealRepository.save(meal);

            log.info("Meal with name={} saved.", dto.getName());
            return meal;
        } catch (EntityAlreadyExistsException e) {
            log.error("Save failed for Meal with name={}. Meal already exists", dto.getName(), e);
            throw e;
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void updateMeal(MealEditDTO dto) throws EntityAlreadyExistsException, EntityNotFoundException {
        try {
            Meal meal = mealRepository.findByUuid(dto.getUuid())
                    .orElseThrow(() -> new EntityNotFoundException("Meal", "Meal not found"));

            if (!meal.getName().equals(dto.getName())) {
                if (mealRepository.findByName(dto.getName()).isPresent()) {
                    throw new EntityAlreadyExistsException
                            ("Meal", "Meal with this name: " + dto.getName() + " already exists");
                }
                meal.setName(dto.getName());
            }

            meal.setIngredients(dto.getIngredients());
            meal.setMealType(dto.getMealType());
            meal.setInstructions(dto.getInstructions());
            meal.setPrepTime(dto.getPrepTime());

            mealRepository.save(meal);

            log.info("Meal with name={} updated.", dto.getName());
        } catch (EntityNotFoundException e) {
            log.error("Update failed for meal with UUID={}. Entity not found.", dto.getUuid(), e);
            throw e;
        } catch (EntityAlreadyExistsException e) {
            log.error("Update failed for meal with name={}. Entity already exists.", dto.getName(), e);
            throw e;
        } catch (Exception e) {
            throw e;
        }
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

    @Override
    public Page<MealReadOnlyDTO> getPaginatedMeals(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Meal> meals = mealRepository.findAll(pageable);
        log.debug("Get paginated meals were returned successfully with page={} and size={}", page, size);
        return meals.map(mapper::mapToMealReadOnlyDTO);
    }

    @Override
    public long getFavoriteMealsCount() {
        return mealRepository.countFavorites();
    }

    @Override
    public Page<MealReadOnlyDTO> getPaginatedMealsByType(MealType mealType, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Meal> meals = mealRepository.findByMealType(mealType, pageable);
        log.debug("Get paginated meals by type {} were returned successfully with page={} and size={}",
                mealType, page, size);
        return meals.map(mapper::mapToMealReadOnlyDTO);
    }

    @Override
    public Page<MealReadOnlyDTO> getPaginatedFavoriteMeals(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());

        Page<Meal> favoriteMealPage = mealRepository.findByFavoriteTrue(pageable);

        log.debug("Get favorite paginated meals were returned successfully with page={} and size={}", page, size);

        if (favoriteMealPage == null) {
            log.warn("No favorite meals found.");
            return Page.empty(pageable);
        }

        return favoriteMealPage.map(mapper::mapToMealReadOnlyDTO);
    }

    @Override
    public Page<MealReadOnlyDTO> getPaginatedFavoriteMealsByType(MealType mealType, int page, int size) {
       Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
       Page<Meal>  favoriteMealPage = mealRepository.findByFavoriteTrueAndMealType(mealType, pageable);
        log.debug("Get favorite paginated meals by type {} were returned successfully with page={} and size={}",
                mealType, page, size);
        return  favoriteMealPage.map(mapper::mapToMealReadOnlyDTO);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Meal getMealByUuid(String uuid) throws EntityNotFoundException {
        try {
            Meal meal = mealRepository.findByUuid(uuid)
                    .orElseThrow(() -> new EntityNotFoundException ("Meal", "Meal with uuid: " + uuid + " not found"));

            log.info("Meal with uuid={} was successfully retrieved", uuid);
            return meal;

        } catch (EntityNotFoundException e) {
            log.error("Failed retrieving meal with uuid={}. Entity not found.", uuid, e);
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void toggleFavoriteMeal(String uuid) throws EntityNotFoundException {
        Meal meal = getMealByUuid(uuid);
        meal.setFavorite(!meal.isFavorite());
        mealRepository.save(meal);
    }
}