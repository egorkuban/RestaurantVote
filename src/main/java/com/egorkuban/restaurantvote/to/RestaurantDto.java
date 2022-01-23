package com.egorkuban.restaurantvote.to;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class RestaurantDto {
    private Long id;
    private String name;
    private String address;
}
