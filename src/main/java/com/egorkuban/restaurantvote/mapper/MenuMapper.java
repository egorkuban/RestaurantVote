package com.egorkuban.restaurantvote.mapper;

import com.egorkuban.restaurantvote.to.DishDto;
import com.egorkuban.restaurantvote.to.MenuDto;

import java.time.LocalDate;
import java.util.List;

public class MenuMapper {
    public static final MenuMapper INSTANCE = new MenuMapper();

    public MenuDto mapToMenuDto (List<DishDto> dishes){
        return new MenuDto()
                .setDishes(dishes)
                .setLocalDate(LocalDate.now());
    }

}
