package com.egorkuban.restaurantvote.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRestaurantRequest {
    private String name;
    private String address;
}
