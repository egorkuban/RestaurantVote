package com.egorkuban.restaurantvote.model.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreateMealRequest {
    private String name;
    private BigDecimal price;
    private LocalDateTime addTime;
}
