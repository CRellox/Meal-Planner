package com.example.Meal_Planner.service;

import com.example.Meal_Planner.core.exceptions.EntityAlreadyExistsException;
import com.example.Meal_Planner.core.exceptions.EntityNotFoundException;
import com.example.Meal_Planner.dto.UserInsertDTO;
import com.example.Meal_Planner.model.User;

public interface IUserService {
    void saveUser(UserInsertDTO userInsertDTO) throws EntityAlreadyExistsException;
    User findByUsername(String username) throws EntityNotFoundException;
}