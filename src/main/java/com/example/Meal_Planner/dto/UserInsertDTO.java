package com.example.Meal_Planner.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInsertDTO {

    @NotNull                  //(message = "Username is required.")
    @Size(min = 2, max = 20) //message = "The username must be between 2 and 20 characters.")
    private String username;

    @NotNull      //(message = "Password is required.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])^.{8,}$")
                // message = "The password must contain at least 1 lowercase letter," +
               //" 1 uppercase letter, 1 digit, and 1 special character, with no spaces.")
    private String password;

    @NotBlank   //(message = "Email is required")
    @Email      //(message = "Please provide a valid email address")
    private String email;
}