package com.egorkuban.restaurantvote.to.request;

import com.egorkuban.restaurantvote.to.MealDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateMealRequest {
    List<MealDto> meals;
}
