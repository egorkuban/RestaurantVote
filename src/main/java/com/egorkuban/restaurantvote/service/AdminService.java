package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.jpa.entity.RestaurantEntity;
import com.egorkuban.restaurantvote.jpa.repository.MealRepository;
import com.egorkuban.restaurantvote.jpa.repository.RestaurantRepository;
import com.egorkuban.restaurantvote.model.RestaurantDto;
import com.egorkuban.restaurantvote.model.request.CreateRestaurantRequest;
import com.egorkuban.restaurantvote.model.response.CreateRestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final RestaurantRepository restaurantRepository;
    private final MealRepository mealRepository;


    @Transactional
    public CreateRestaurantResponse createRestaurant(CreateRestaurantRequest request) {
        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setName(request.getName());
        restaurant.setAddress(request.getAddress());
        restaurantRepository.save(restaurant);

        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setName(request.getName());
        restaurantDto.setAddress(request.getAddress());
        restaurantDto.setId(restaurant.getId());

        CreateRestaurantResponse restaurantResponse = new CreateRestaurantResponse();
        restaurantResponse.setRestaurantDto(restaurantDto);


        return restaurantResponse;
    }
}
