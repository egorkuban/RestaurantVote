package com.egorkuban.restaurantvote.model.request;

import com.egorkuban.restaurantvote.model.MealDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateMealRequest {
    List<MealDto> meals;
}
