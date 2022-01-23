package com.egorkuban.restaurantvote.to;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class MenuDto {
    private List<DishDto> dishes;
    private LocalDate localDate;

}
