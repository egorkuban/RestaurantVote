package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.jpa.entity.MealEntity;
import com.egorkuban.restaurantvote.jpa.entity.RestaurantEntity;
import com.egorkuban.restaurantvote.jpa.repository.RestaurantRepository;
import com.egorkuban.restaurantvote.mapper.MealMapper;
import com.egorkuban.restaurantvote.mapper.RestaurantMapper;
import com.egorkuban.restaurantvote.model.request.CreateMealRequest;
import com.egorkuban.restaurantvote.model.request.CreateRestaurantRequest;
import com.egorkuban.restaurantvote.model.response.CreatMealResponse;
import com.egorkuban.restaurantvote.model.response.CreateRestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper mapper;
    private final MealMapper mealMapper;


    @Transactional
    public CreateRestaurantResponse createRestaurant(CreateRestaurantRequest request) {
        RestaurantEntity restaurant = new RestaurantEntity()
                .setName(request.getName())
                .setAddress(request.getAddress());
        restaurantRepository.save(restaurant);

        return new CreateRestaurantResponse()
                .setRestaurantDto(mapper.mapToRestaurantDto(restaurant));
    }

    @Transactional
    public void deleteRestaurant(Long id) {
        RestaurantEntity restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found by Id " + id));
        restaurantRepository.delete(restaurant);
    }

    @Transactional
    public CreatMealResponse createMeals(CreateMealRequest request, Long id) {
        RestaurantEntity restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found by Id " + id));

        List<MealEntity> mealEntities = mealMapper.mapToMealsEntity(request);
        restaurant.getMeals().clear();
        mealEntities.forEach(e -> e.setRestaurant(restaurant));
        restaurant.getMeals().addAll(mealEntities);

        restaurantRepository.save(restaurant);

        return new CreatMealResponse()
                .setRestaurantDto(mapper.mapToRestaurantDto(restaurant));
    }

}
