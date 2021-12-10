package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.model.MealDto;
import com.egorkuban.restaurantvote.model.RestaurantDto;
import com.egorkuban.restaurantvote.model.request.CreateMealRequest;
import com.egorkuban.restaurantvote.model.request.CreateRestaurantRequest;
import com.egorkuban.restaurantvote.model.response.CreatMealResponse;
import com.egorkuban.restaurantvote.model.response.CreateRestaurantResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AdminService {

    static final List<CreateRestaurantResponse> RESTAURANTS_LIST = new ArrayList<>();
    static final List<RestaurantDto> RESTAURANTS_WITH_MEALS_LIST = new ArrayList<>();

    private static final AtomicLong RESTAURANT_ID = new AtomicLong(100000);


    @Transactional
    public CreateRestaurantResponse createRestaurant(CreateRestaurantRequest createRestaurantRequest) {
        final long restaurantId = RESTAURANT_ID.incrementAndGet();


        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setAddress(createRestaurantRequest.getAddress());
        restaurantDto.setName(createRestaurantRequest.getName());

        CreateRestaurantResponse createRestaurantResponse = new CreateRestaurantResponse();
        createRestaurantResponse.setId(restaurantId);
        createRestaurantResponse.setRestaurantDto(restaurantDto);
        RESTAURANTS_LIST.add(createRestaurantResponse);
        return createRestaurantResponse;
    }

    @Transactional
    public List<CreatMealResponse> createMealsList(CreateMealRequest request, Long id) {
        //из еды делаем необходимый нам список
        return null;
    }

    @Transactional
    public RestaurantDto createMeals(CreateMealRequest request, Long id) {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setMeals(createMealsList(request, id));
        restaurantDto.setId(id);
        return restaurantDto;
    }

    @Transactional
    public Long deleteRestaurant(Long id) {
        RESTAURANTS_LIST.removeIf(i -> i.getId().equals(id));
        return id;
    }
}
