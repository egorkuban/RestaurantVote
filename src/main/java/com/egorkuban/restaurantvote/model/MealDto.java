package com.egorkuban.restaurantvote.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class MealDto {
    private String name;
    private BigDecimal price;
    private LocalDateTime addTime;
}
