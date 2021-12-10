package com.egorkuban.restaurantvote.model.dto;

import com.egorkuban.restaurantvote.model.Meal;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MealTo {
    private String name;
    private BigDecimal price;
    private LocalDateTime addTime;
    private Long id;
}
