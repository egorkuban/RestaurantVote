package com.egorkuban.restaurantvote.mapper;

import com.egorkuban.restaurantvote.jpa.model.Dish;
import com.egorkuban.restaurantvote.to.DishDto;

import java.util.Collections;
import java.util.List;

public class DishMapper {

    public static final DishMapper INSTANCE = new DishMapper();

    public List<Dish> mapToEntity(List<DishDto> meals) {
        if (meals == null) {
            return Collections.emptyList();
        }
        return meals.stream()
                .map(this::mapToEntity)
                .toList();
    }

    private Dish mapToEntity(DishDto dishDto) {
        if (dishDto == null) {
            return null;
        }
        return new Dish()
                .setName(dishDto.getName())
                .setPrice(dishDto.getPrice());
    }

    public DishDto mapToDto(Dish dish) {
        if (dish == null) {
            return null;
        }
        return new DishDto()
                .setName(dish.getName())
                .setPrice(dish.getPrice());
    }

    public List<DishDto> mapToDto(List<Dish> dishes) {
        if (dishes == null) {
            return null;
        }
        return dishes.stream()
                .map(this::mapToDto)
                .toList();
    }
}
