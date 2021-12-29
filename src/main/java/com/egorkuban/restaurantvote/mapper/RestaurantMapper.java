package com.egorkuban.restaurantvote.mapper;

import com.egorkuban.restaurantvote.jpa.entity.RestaurantEntity;
import com.egorkuban.restaurantvote.model.RestaurantDto;

public class RestaurantMapper {
    public final static RestaurantMapper INSTANCE = new RestaurantMapper();

    public RestaurantDto mapToRestaurantDto(RestaurantEntity entity) {
        return new RestaurantDto()
                .setId(entity.getId())
                .setName(entity.getName())
                .setAddress(entity.getAddress())
                .setMeals(MealMapper.INSTANCE.mapToMealsDto(entity.getMeals()));
    }
}
