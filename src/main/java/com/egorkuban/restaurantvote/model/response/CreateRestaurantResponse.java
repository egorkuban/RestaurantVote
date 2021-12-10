package com.egorkuban.restaurantvote.model.response;

import com.egorkuban.restaurantvote.model.RestaurantDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRestaurantResponse {
    Long id;
    RestaurantDto restaurantDto;
}