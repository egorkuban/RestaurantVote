package com.egorkuban.restaurantvote.mapper;

import com.egorkuban.restaurantvote.jpa.entity.RestaurantEntity;
import com.egorkuban.restaurantvote.model.RestaurantDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestaurantMapper {
    public final static RestaurantMapper RESTAURANT_INSTANT = new RestaurantMapper();
    public RestaurantDto mapToRestaurantDto(RestaurantEntity entity) {
        return new RestaurantDto()
                .setId(entity.getId())
                .setName(entity.getName())
                .setAddress(entity.getAddress())
                .setMeals(MealMapper.MEAL_INSTANT.mapToMealsDto(entity.getMeals()));
    }
}
