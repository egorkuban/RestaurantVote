package com.egorkuban.restaurantvote.mapper;

import com.egorkuban.restaurantvote.jpa.model.Meal;
import com.egorkuban.restaurantvote.to.MealDto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class MealMapper {

    public static final MealMapper INSTANCE = new MealMapper();

    public List<Meal> mapToMeals(List<MealDto> meals) {
        if (meals == null) {
            return Collections.emptyList();
        }
        return meals.stream()
                .map(this::mapToMeal)
                .toList();
    }

    private Meal mapToMeal(MealDto mealDto) {
        return new Meal()
                .setName(mealDto.getName())
                .setPrice(mealDto.getPrice())
                .setDate(LocalDate.now());
    }

    public List<MealDto> mapToMealsDto(List<Meal> meals) {
        if (meals == null) {
            return Collections.emptyList();
        }
        return meals.stream()
                .map(meal -> new MealDto()
                        .setName(meal.getName())
                        .setPrice(meal.getPrice()))
                .toList();
    }
}
