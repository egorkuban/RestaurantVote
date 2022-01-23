package com.egorkuban.restaurantvote.to.request;

import com.egorkuban.restaurantvote.to.DishDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CreateMealRequest {
    List<DishDto> dishes;
    LocalDate menuDate;
}
