package com.egorkuban.restaurantvote.mapper;

import com.egorkuban.restaurantvote.jpa.model.Restaurant;
import com.egorkuban.restaurantvote.to.RestaurantDto;

public class RestaurantMapper {
    public static final RestaurantMapper INSTANCE = new RestaurantMapper();

    public RestaurantDto mapToRestaurantDto(Restaurant restaurant) {
        return new RestaurantDto()
                .setId(restaurant.getId())
                .setName(restaurant.getName())
                .setAddress(restaurant.getAddress());
    }
}
