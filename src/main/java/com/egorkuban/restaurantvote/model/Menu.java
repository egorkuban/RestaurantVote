package com.egorkuban.restaurantvote.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Menu {
    private Long restaurantId;
    private List<Meal> meals;
}
