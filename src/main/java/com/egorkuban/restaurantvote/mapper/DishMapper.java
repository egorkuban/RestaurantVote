package com.egorkuban.restaurantvote.mapper;

import com.egorkuban.restaurantvote.jpa.model.Dish;
import com.egorkuban.restaurantvote.to.DishDto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class DishMapper {

    public static final DishMapper INSTANCE = new DishMapper();

    public List<Dish> mapToEntity(List<DishDto> meals) {
        if (meals == null) {
            return Collections.emptyList();
        }
        return meals.stream()
                .map(this::mapToMeal)
                .toList();
    }

    private Dish mapToMeal(DishDto dishDto) {
        return new Dish()
                .setName(dishDto.getName())
                .setPrice(dishDto.getPrice())
                .setDate(LocalDate.now());
    }
}
