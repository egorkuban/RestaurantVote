package com.egorkuban.restaurantvote.model.dto;

import com.egorkuban.restaurantvote.model.Meal;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantTo {
    private Long id;
    private String name;
    private String address;
}
