package com.egorkuban.restaurantvote.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
public class MealDto {
    private String name;
    private BigDecimal price;
    private LocalDateTime addTime;
}
