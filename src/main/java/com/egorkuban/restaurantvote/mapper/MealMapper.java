package com.egorkuban.restaurantvote.mapper;

import com.egorkuban.restaurantvote.jpa.entity.MealEntity;
import com.egorkuban.restaurantvote.model.MealDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Configuration
public class MealMapper {
    public List<MealEntity> mapToMealsEntity(List<MealDto> meals) {
        return !(meals == null)
                ? meals.stream()
                .map(this::mapToMealEntity)
                .collect(Collectors.toList())
                : Collections.emptyList();
    }
    private MealEntity mapToMealEntity (MealDto mealDto){
        return new MealEntity()
                .setName(mealDto.getName())
                .setPrice(mealDto.getPrice());
    }
}
