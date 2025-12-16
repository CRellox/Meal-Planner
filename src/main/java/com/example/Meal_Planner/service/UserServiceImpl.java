package com.example.Meal_Planner.service;

import com.example.Meal_Planner.core.exceptions.EntityAlreadyExistsException;
import com.example.Meal_Planner.core.exceptions.EntityNotFoundException;
import com.example.Meal_Planner.dto.UserInsertDTO;
import com.example.Meal_Planner.mapper.Mapper;
import com.example.Meal_Planner.model.User;
import com.example.Meal_Planner.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Mapper mapper;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void saveUser(UserInsertDTO userInsertDTO) throws EntityAlreadyExistsException {
        try {
            if (userRepository.findByUsername(userInsertDTO.getUsername()).isPresent()) {
                throw new EntityAlreadyExistsException("User",
                        "User with username: " + userInsertDTO.getUsername() + " already exists.");
            }

            if (userRepository.findByEmail(userInsertDTO.getEmail()).isPresent()) {
                throw new EntityAlreadyExistsException("User",
                        "User with email: " + userInsertDTO.getEmail() + " already exists.");
            }

            User newUser = mapper.mapToUserEntity(userInsertDTO);
            newUser.setPassword(passwordEncoder.encode(userInsertDTO.getPassword()));

            userRepository.save(newUser);
            log.info("Registration succeeded for user with username={}", userInsertDTO.getUsername());

        } catch (EntityAlreadyExistsException e) {
            log.error("Registration failed: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public User findByUsername(String username) throws EntityNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(
                        "User", "User with username: " + username + " not found"));
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}