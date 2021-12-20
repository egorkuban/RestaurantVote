package com.egorkuban.restaurantvote.mapper;

import com.egorkuban.restaurantvote.jpa.entity.MealEntity;
import com.egorkuban.restaurantvote.model.MealDto;
import com.egorkuban.restaurantvote.model.request.CreateMealRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Configuration
public class MealMapper {
    public List<MealEntity> mapToMealsEntity (CreateMealRequest request){
        return request.getMeals().stream()
                .map(mealDto -> new MealEntity()
                        .setName(mealDto.getName())
                        .setPrice(mealDto.getPrice()))
                .collect(Collectors.toList());
    }
}
