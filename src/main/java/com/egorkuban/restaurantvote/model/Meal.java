package com.egorkuban.restaurantvote.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Meal {
    private String name;
    private BigDecimal price;
}
