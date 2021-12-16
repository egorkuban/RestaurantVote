package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.jpa.entity.MealEntity;
import com.egorkuban.restaurantvote.jpa.entity.RestaurantEntity;
import com.egorkuban.restaurantvote.jpa.repository.MealRepository;
import com.egorkuban.restaurantvote.jpa.repository.RestaurantRepository;
import com.egorkuban.restaurantvote.model.MealDto;
import com.egorkuban.restaurantvote.model.RestaurantDto;
import com.egorkuban.restaurantvote.model.request.CreateMealRequest;
import com.egorkuban.restaurantvote.model.request.CreateRestaurantRequest;
import com.egorkuban.restaurantvote.model.response.CreatMealResponse;
import com.egorkuban.restaurantvote.model.response.CreateRestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final RestaurantRepository restaurantRepository;
    private final MealRepository mealRepository;


    @Transactional
    public CreateRestaurantResponse createRestaurant(CreateRestaurantRequest request) {
        RestaurantEntity restaurant = new RestaurantEntity()
                .setName(request.getName())
                .setAddress(request.getAddress());
        restaurantRepository.save(restaurant);

        RestaurantDto restaurantDto = new RestaurantDto()
                .setName(restaurant.getName())
                .setAddress(restaurant.getAddress())
                .setId(restaurant.getId());

        return new CreateRestaurantResponse()
                .setRestaurantDto(restaurantDto);
    }

    @Transactional
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    @Transactional
    public CreatMealResponse createMeals(CreateMealRequest request, Long id) {
        List<MealEntity> mealEntities = request.getMeals().stream()
                .map(mealRequest -> {
                    MealEntity mealEntity = new MealEntity()
                            .setName(mealRequest.getName())
                            .setPrice(mealRequest.getPrice())
                            .setAddTime(mealRequest.getAddTime())
                            .setRestaurant(restaurantRepository.getById(id));
                    mealRepository.save(mealEntity);
                    return mealEntity;
                })
                .collect(Collectors.toList());

        RestaurantEntity restaurant = restaurantRepository.getById(id)
                .setMeals(mealEntities);

        restaurantRepository.save(restaurant);

        List<MealDto> meals = request.getMeals().stream()
                .map(mealDto -> new MealDto()
                        .setName(mealDto.getName())
                        .setPrice(mealDto.getPrice())
                        .setAddTime(mealDto.getAddTime())
                )
                .collect(Collectors.toList());

        RestaurantDto restaurantDto = new RestaurantDto()
                .setName(restaurant.getName())
                .setAddress(restaurant.getAddress())
                .setId(restaurant.getId())
                .setMeals(meals);

        return new CreatMealResponse()
                .setRestaurantDto(restaurantDto);
    }

}
