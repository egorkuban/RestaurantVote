package com.egorkuban.restaurantvote.mapper;

import com.egorkuban.restaurantvote.jpa.entity.MealEntity;
import com.egorkuban.restaurantvote.jpa.entity.RestaurantEntity;
import com.egorkuban.restaurantvote.model.MealDto;
import com.egorkuban.restaurantvote.model.RestaurantDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Configuration
public class RestaurantMapper {

    public RestaurantDto mapToRestaurantDto(RestaurantEntity entity) {
        RestaurantDto restaurantDto = new RestaurantDto()
                .setId(entity.getId())
                .setName(entity.getName())
                .setAddress(entity.getAddress())
                .setMeals(mapToMealsDto(entity.getMeals()));
        return restaurantDto;

    }

    private List<MealDto> mapToMealsDto(List<MealEntity> meals) {
        if (meals == null) {
            return null;
        }
        return meals.stream()
                .map(meal -> new MealDto()
                        .setName(meal.getName())
                        .setPrice(meal.getPrice()))
                .collect(Collectors.toList());

    }
}
