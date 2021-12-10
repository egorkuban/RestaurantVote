package com.egorkuban.restaurantvote.model.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreatMealResponse {
    private String name;
    private BigDecimal price;
    private Long restaurantId;
    private Long id;
}