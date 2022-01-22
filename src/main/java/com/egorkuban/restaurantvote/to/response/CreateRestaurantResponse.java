package com.egorkuban.restaurantvote.to.response;

import com.egorkuban.restaurantvote.to.RestaurantDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CreateRestaurantResponse {
    RestaurantDto restaurantDto;
}