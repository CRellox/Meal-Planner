package com.example.Meal_Planner.controller;

import com.example.Meal_Planner.core.exceptions.EntityAlreadyExistsException;
import com.example.Meal_Planner.dto.UserInsertDTO;
import com.example.Meal_Planner.repository.UserRepository;
import com.example.Meal_Planner.service.UserService;
import com.example.Meal_Planner.validator.UserInsertValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mealplanner/")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final UserInsertValidator userInsertValidator;
    private final UserRepository userRepository;

    @GetMapping("/register")
    public String getUserForm(Model model) {
        model.addAttribute("userInsertDTO", new UserInsertDTO());
        model.addAttribute("user",userRepository.findAll(Sort.by("username")));
        return "registration-form";
    }

    @PostMapping("/register")
    public String insertUser(@Valid @ModelAttribute("userInsertDTO") UserInsertDTO userInsertDTO,
                             BindingResult bindingResult, Model model) {

        userInsertValidator.validate(userInsertDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userRepository.findAll(Sort.by("username")));
            return "registration-form";
        }

        try {
            userService.saveUser(userInsertDTO);
            return "redirect:/";
        } catch (EntityAlreadyExistsException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "registration-form";
        }
    }
}