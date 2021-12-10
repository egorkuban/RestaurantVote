package com.egorkuban.restaurantvote.model;

import com.egorkuban.restaurantvote.model.response.CreatMealResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RestaurantDto {
    private Long id;
    private String name;
    private String Address;
    List<MealDto> meals;
}
