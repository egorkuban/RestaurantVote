package com.egorkuban.restaurantvote.mapper;

import com.egorkuban.restaurantvote.jpa.entity.RestaurantEntity;
import com.egorkuban.restaurantvote.model.MealDto;
import com.egorkuban.restaurantvote.model.RestaurantDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Getter
@Setter
@Configuration
public class RestaurantMapper {

    public RestaurantDto mapToRestaurantDto(RestaurantEntity entity) {
        return new RestaurantDto()
                .setId(entity.getId())
                .setName(entity.getName())
                .setAddress(entity.getAddress())
                .setMeals(entity.getMeals().
                        stream().map(meal -> new MealDto()
                                .setName(meal.getName())
                                .setPrice(meal.getPrice()))
                        .collect(Collectors.toList()));
    }
}
