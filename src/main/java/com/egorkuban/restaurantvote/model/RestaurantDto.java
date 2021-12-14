package com.egorkuban.restaurantvote.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantDto {
    private Long id;
    private String name;
    private String address;
    List<MealDto> meals;

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", id=" + id +
                '}';
    }
}
