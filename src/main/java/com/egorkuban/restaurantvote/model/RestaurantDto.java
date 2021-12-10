package com.egorkuban.restaurantvote.model;

import com.egorkuban.restaurantvote.model.response.CreatMealResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantDto {
    private Long id;
    private List<CreatMealResponse> meals;
}
