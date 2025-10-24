package com.example.Meal_Planner.controller;


import com.example.Meal_Planner.model.Meal;
import com.example.Meal_Planner.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealRepository mealRepository;


    @GetMapping("/main")
    public String listMeals(Model model) {
        model.addAttribute("meals", mealRepository.findAll());
        return "meals/main";
    }

    @GetMapping("/new")
    public String showMealForm(Model model) {
        model.addAttribute("meal", new Meal());
        return "meals/new";
    }

    @PostMapping("/save")
    public String saveMeal(@ModelAttribute Meal meal) {
        mealRepository.save(meal);
        return "redirect:/meals/main";
    }

    @GetMapping("/delete")
    public String deleteMeal(@ModelAttribute long id) {
        mealRepository.deleteById(id);
        return "redirect:/meals/main";
    }
}