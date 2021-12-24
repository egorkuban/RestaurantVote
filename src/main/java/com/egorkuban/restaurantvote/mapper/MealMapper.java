package com.egorkuban.restaurantvote.mapper;

import com.egorkuban.restaurantvote.jpa.entity.MealEntity;
import com.egorkuban.restaurantvote.model.MealDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Component
public class MealMapper {

    public List<MealEntity> mapToMealsEntity(List<MealDto> meals) {
        if (meals == null) {
            return Collections.emptyList();
        }
        return meals.stream()
                .map(this::mapToMealEntity)
                .collect(Collectors.toList());
    }

    private MealEntity mapToMealEntity(MealDto mealDto) {
        return new MealEntity()
                .setName(mealDto.getName())
                .setPrice(mealDto.getPrice());
    }

    public List<MealDto> mapToMealsDto(List<MealEntity> meals) {
        if (meals == null) {
            return Collections.emptyList();
        }
        return meals.stream()
                .map(meal -> new MealDto()
                        .setName(meal.getName())
                        .setPrice(meal.getPrice()))
                .collect(Collectors.toList());

    }
}
