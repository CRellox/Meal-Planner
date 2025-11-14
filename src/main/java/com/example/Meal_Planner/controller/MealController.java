package com.example.Meal_Planner.controller;

import com.example.Meal_Planner.core.enums.MealType;
import com.example.Meal_Planner.core.exceptions.EntityAlreadyExistsException;
import com.example.Meal_Planner.core.exceptions.EntityNotFoundException;
import com.example.Meal_Planner.dto.MealInsertDTO;
import com.example.Meal_Planner.model.Meal;
import com.example.Meal_Planner.repository.MealRepository;
import com.example.Meal_Planner.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mealplanner/meal")
@RequiredArgsConstructor
public class MealController {

    private final MealRepository mealRepository;
    private final MealService mealService;

    @GetMapping("/list")
    public String listMeals(@RequestParam(required = false) String mealType, Model model) {
        List<Meal> meals;

        if (mealType != null && !mealType.isEmpty() && !mealType.equals("all")) {
            meals = mealRepository.findByMealType(MealType.valueOf(mealType));
        } else {
            meals = mealRepository.findAll();
        }

        model.addAttribute("meals", meals);
        model.addAttribute("selectedMealType", mealType != null ? mealType : "all");
        return "meal-list";
    }

    @GetMapping("/insert")
    public String showMealForm(Model model) {
        model.addAttribute("mealInsertDTO", new MealInsertDTO());
        return "meal-form";
    }

    @PostMapping("/insert")
    public String saveMeal(@ModelAttribute("mealInsertDTO") MealInsertDTO mealInsertDTO, Model model) {
        try {
            mealService.saveMeal(mealInsertDTO);
            return "redirect:/mealplanner/meal/list";
        } catch (EntityAlreadyExistsException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("mealInsertDTO", mealInsertDTO);
            return "meal-form";
        }
    }

    @GetMapping("/delete/{uuid}")
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
}