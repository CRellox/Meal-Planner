package com.example.Meal_Planner.controller;

import com.example.Meal_Planner.core.enums.MealType;
import com.example.Meal_Planner.core.exceptions.EntityAlreadyExistsException;
import com.example.Meal_Planner.core.exceptions.EntityNotFoundException;
import com.example.Meal_Planner.dto.MealEditDTO;
import com.example.Meal_Planner.dto.MealInsertDTO;
import com.example.Meal_Planner.dto.MealReadOnlyDTO;
import com.example.Meal_Planner.mapper.Mapper;
import com.example.Meal_Planner.model.Meal;
import com.example.Meal_Planner.repository.MealRepository;
import com.example.Meal_Planner.service.IMealService;
import com.example.Meal_Planner.validator.MealEditValidator;
import com.example.Meal_Planner.validator.MealInsertValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

//import java.util.List;

@Controller
@RequestMapping("/mealplanner/meal")
@RequiredArgsConstructor
@Slf4j
public class MealController {

    private final MealRepository mealRepository;
    private final IMealService mealService;
    private final Mapper mapper;
    private final MealInsertValidator mealInsertValidator;
    private final MealEditValidator mealEditValidator;

//    @GetMapping("/list")
//    public String listMeals(@RequestParam(required = false) String mealType, Model model) {
//        List<Meal> meals;
//
//        if (mealType != null && !mealType.isEmpty() && !mealType.equals("all")) {
//            meals = mealRepository.findByMealType(MealType.valueOf(mealType));
//        } else {
//            meals = mealRepository.findAll();
//        }
//
//        model.addAttribute("meals", meals);
//        // To sort the meal types
//        model.addAttribute("selectedMealType", mealType != null ? mealType : "all");
//        return "meal-list";
//    }

    @GetMapping("/insert")
    public String showMealForm(Model model) {
        model.addAttribute("mealInsertDTO", new MealInsertDTO());
        return "meal-form";
    }

    @PostMapping("/insert")
    public String saveMeal(@Valid @ModelAttribute("mealInsertDTO")
                               MealInsertDTO mealInsertDTO, BindingResult bindingResult, Model model) {

        Meal savedMeal;

        mealInsertValidator.validate(mealInsertDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            log.error("Validation errors: {}", bindingResult.getAllErrors());
            return "meal-form";
        }

        try {
            savedMeal = mealService.saveMeal(mealInsertDTO);
            return "redirect:/mealplanner/meal/list";
        } catch (EntityAlreadyExistsException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "meal-form";
        }
    }

    @PostMapping("/edit/{uuid}")
    public String updateMeal(@PathVariable String uuid, @Valid @ModelAttribute("mealEditDTO")
                             MealEditDTO mealEditDTO, BindingResult bindingResult, Model model) {

        mealEditValidator.validate(mealEditDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("names", mealRepository.findAll(Sort.by("name")));
            return "meal-edit-form";
        }

        try {
            mealService.updateMeal(mealEditDTO);
            return "redirect:/mealplanner/meal/list";
        } catch (EntityNotFoundException | EntityAlreadyExistsException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "meal-edit-form";
        }
    }

    @GetMapping("/edit/{uuid}")
    public String showMealEditForm(@PathVariable String uuid, Model model) {
        try {
            Meal meal = mealRepository.findByUuid(uuid)
                    .orElseThrow(() -> new EntityNotFoundException("Meal", "Meal not found"));

            model.addAttribute("mealEditDTO", mapper.mapToMealEditDTO(meal));
            model.addAttribute("names", mealRepository.findAll(Sort.by("name")));

            return "meal-edit-form";
        } catch (EntityNotFoundException e) {
            log.error("Meal with uuid={} was not found.", uuid, e);

            model.addAttribute("names", mealRepository.findAll(Sort.by("name")));
            model.addAttribute("errorMessage", e.getMessage());

            return "meal-edit-form";
        }
    }

    @PostMapping("/delete/{uuid}")
    public String deleteMeal(@PathVariable String uuid, Model model) {
        try {
            mealService.deleteMealByUUID(uuid);
            return "redirect:/mealplanner/meal/list";
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "meal-list";
        }
    }

    @GetMapping("/main-menu")
    public String showMainMenu() {
        return "main-menu";
    }

    @GetMapping("/list")
    public String getPaginatedMeals(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String mealType,
            Model model) {

        Page<MealReadOnlyDTO> mealsPage;

        if (mealType != null && !mealType.isEmpty() && !mealType.equals("all")) {
            mealsPage = mealService.getPaginatedMealsByType(MealType.valueOf(mealType), page, size);
        } else {
            mealsPage = mealService.getPaginatedMeals(page, size);
        }

        model.addAttribute("mealsPage", mealsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", mealsPage.getTotalPages());
        model.addAttribute("selectedMealType", mealType != null ? mealType : "all");

        return "meal-list";
    }

    @GetMapping("/list/favorites")
    public String getPaginatedFavoriteMeals(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String mealType,
            Model model) {

        Page<MealReadOnlyDTO> favoritesPage;

        if (mealType != null && !mealType.isEmpty() && !mealType.equals("all")) {
            favoritesPage = mealService.getPaginatedFavoriteMealsByType(
                    MealType.valueOf(mealType), page, size);
        } else {
            favoritesPage = mealService.getPaginatedFavoriteMeals(page, size);
        }

        if (favoritesPage == null) {
            Pageable pageable = PageRequest.of(page, size);
            favoritesPage = Page.empty(pageable);
        }

        long favoritesCount = mealService.getFavoriteMealsCount();

        model.addAttribute("mealsPage", favoritesPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("selectedMealType", mealType != null ? mealType : "all");
        model.addAttribute("favoritesCount", favoritesCount);

        return "meal-favorites";
    }

    @GetMapping("/view/{uuid}")
    public String viewMeal(@PathVariable String uuid, Model model) {
        try {
            Meal meal = mealService.getMealByUuid(uuid);
            model.addAttribute("meal", meal);
            return "meal-details";
        } catch (EntityNotFoundException e) {
            return "redirect:/mealplanner/meal/list?error=not_found";
        }
    }

//    @GetMapping("/favorites")
//    public String viewFavoriteList() {
//        return "meal-favorites";
//    }

    @PostMapping("/toggle-favorite/{uuid}")
    public String toggleFavorite(@PathVariable String uuid,
                                 @RequestParam(required = false) String redirect) throws EntityNotFoundException {
        Meal savedMeal;

            Meal meal = mealRepository.findByUuid(uuid)
                    .orElseThrow(() -> new EntityNotFoundException("Meal", "Meal not found with UUID: " + uuid));

            meal.setFavorite(!meal.isFavorite());
            savedMeal =  mealRepository.save(meal);

        if (redirect != null && redirect.equals("favorites")) {
            return "redirect:/mealplanner/meal/list/favorites";
        }
        return "redirect:/mealplanner/meal/list";
    }
}