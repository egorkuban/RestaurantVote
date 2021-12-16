package com.egorkuban.restaurantvote.model.response;

import com.egorkuban.restaurantvote.model.RestaurantDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CreateRestaurantResponse {
    RestaurantDto restaurantDto;
    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + restaurantDto.getName() + '\'' +
                ", address=" + restaurantDto.getAddress() +
                ", id=" + restaurantDto.getId() +
                '}';
    }
}