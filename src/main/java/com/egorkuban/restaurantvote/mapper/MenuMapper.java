package com.egorkuban.restaurantvote.mapper;

import com.egorkuban.restaurantvote.jpa.model.Meal;
import com.egorkuban.restaurantvote.jpa.model.Menu;
import com.egorkuban.restaurantvote.jpa.model.Restaurant;
import com.egorkuban.restaurantvote.to.MealDto;
import com.egorkuban.restaurantvote.to.MenuDto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class MenuMapper {
    public static final MenuMapper INSTANCE = new MenuMapper();

    public MenuDto mapToMenuDto (List<MealDto> meals){
        return new MenuDto()
                .setMeals(meals)
                .setLocalDate(LocalDate.now());
    }
    public Menu mapToMenu (List<Meal> meals, Restaurant restaurant) {
        return new Menu()
                .setMeals(meals)
                .setDate(LocalDate.now())
                .setRestaurant(restaurant)
                .setIsActual(true)
                .setDateCreate(LocalDate.now());
    }
    public Menu mapToEmptyMenu (Restaurant restaurant){
        return new Menu()
                .setMeals(Collections.emptyList())
                .setIsActual(true)
                .setRestaurant(restaurant)
                .setDateCreate(LocalDate.now())
                .setDate(LocalDate.now());
    }

}
