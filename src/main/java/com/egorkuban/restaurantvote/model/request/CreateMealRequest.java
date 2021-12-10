package com.egorkuban.restaurantvote.model.request;

import com.egorkuban.restaurantvote.model.MealDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CreateMealRequest {
    private String name;
    private BigDecimal price;
    List<MealDto> meals;
}
