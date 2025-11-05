package com.example.Meal_Planner.controller;


import com.example.Meal_Planner.core.exceptions.EntityNotFoundException;
import com.example.Meal_Planner.dto.MealInsertDTO;
import com.example.Meal_Planner.model.Meal;
import com.example.Meal_Planner.repository.MealRepository;
import com.example.Meal_Planner.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/MealPlanner")
@RequiredArgsConstructor
public class MealController {

    private final MealRepository mealRepository;
    private final MealService mealService;


    @GetMapping("/meal-list")
    public String listMeals(Model model) {
        model.addAttribute("meals", mealRepository.findAll());
        return "meal-list";
    }

    @GetMapping("/meal-form")
    public String showMealForm(Model model) {
        model.addAttribute("mealInsertDTO", new MealInsertDTO());
        return "meal-form";
    }

    @PostMapping("/meal-form")
    public String saveMeal(@ModelAttribute Meal meal) {
        mealRepository.save(meal);
        return "redirect:/MealPlanner/meal-list";
    }

    @GetMapping("/delete/{uuid}")
    public String deleteMeal(@PathVariable String uuid, Model model) {
        try {
            mealService.deleteMealByUUID(uuid);
            return "redirect:/MealPlanner/meal-list";
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "meal-list";
        }
    }
}