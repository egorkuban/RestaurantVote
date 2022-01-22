package com.egorkuban.restaurantvote.to.response;

import com.egorkuban.restaurantvote.to.MenuDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CreatMealResponse {
    MenuDto menuDto;
}
