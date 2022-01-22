package com.egorkuban.restaurantvote.mapper;

import com.egorkuban.restaurantvote.to.MealDto;
import com.egorkuban.restaurantvote.to.MenuDto;

import java.time.LocalDate;
import java.util.List;

public class MenuMapper {
    public static final MenuMapper INSTANCE = new MenuMapper();

    public MenuDto mapToMenuDto (List<MealDto> meals){
        return new MenuDto()
                .setMeals(meals)
                .setLocalDate(LocalDate.now());
    }
}
