package com.example.Meal_Planner.repository;

import java.util.List;
import com.example.Meal_Planner.core.enums.MealType;
import com.example.Meal_Planner.model.Meal;
import com.example.Meal_Planner.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MealRepository extends JpaRepository<Meal, Long>, JpaSpecificationExecutor<Meal> {

//    Optional<Meal> findByUuid(String uuid);
//    List<Meal> findByMealType(MealType mealType);
//    long countByFavoriteTrue();
//    List<Meal> findByUser(User user);
//    Page<Meal> findByFavoriteTrue(Pageable pageable);
//    Optional<Meal> findByName(String name);
//    Page<Meal> findAllByUsername(Pageable pageable, String user);
//    Optional<Meal> findByUser_Username(String username);
//    Page<Meal> findByMealType(MealType mealType, String user, Pageable pageable);
//    Page<Meal> findByFavoriteTrueAndMealType(MealType mealType, Pageable pageable);


    Optional<Meal> findByUuidAndUser(String uuid, User user);

    Page<Meal> findAllByUser(User user, Pageable pageable);

    Page<Meal> findByUserAndMealType(User user, MealType mealType, Pageable pageable);

    Page<Meal> findByUserAndFavoriteTrue(User user, Pageable pageable);

    Page<Meal> findByUserAndFavoriteTrueAndMealType(User user, MealType mealType, Pageable pageable);

    Optional<Meal> findByNameAndUser(String name, User user);

    long countByUserAndFavoriteTrue(User user);

    @Query("SELECT COUNT(m) FROM Meal m WHERE m.favorite = true")
    long countFavorites();
}