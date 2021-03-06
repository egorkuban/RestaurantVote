package com.egorkuban.restaurantvote.to;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class DishDto {
    private String name;
    private BigDecimal price;
}
