package com.example.Meal_Planner.validator;

import com.example.Meal_Planner.dto.UserInsertDTO;
import com.example.Meal_Planner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserInsertValidator implements Validator {
    private final UserRepository userRepository;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return UserInsertDTO.class == clazz;
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        UserInsertDTO userInsertDTO = (UserInsertDTO) target;

        if (userRepository.findByUsername(userInsertDTO.getUsername()).isPresent()) {
            log.error("Save failed for user  with username={}.", userInsertDTO.getUsername());
            errors.rejectValue("username", "username.user.exists");
        }

        if (userRepository.findByEmail(userInsertDTO.getEmail()).isPresent()) {
            log.error("Save failed for user with email={}.", userInsertDTO.getEmail());
            errors.rejectValue("email", "email.user.exists");
        }
    }
}