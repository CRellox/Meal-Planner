package com.example.Meal_Planner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MealPlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MealPlannerApplication.class, args);
	}

}
