package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.jpa.entity.MealEntity;
import com.egorkuban.restaurantvote.jpa.entity.RestaurantEntity;
import com.egorkuban.restaurantvote.jpa.repository.MealRepository;
import com.egorkuban.restaurantvote.jpa.repository.RestaurantRepository;
import com.egorkuban.restaurantvote.mapper.RestaurantMapper;
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
    private final RestaurantMapper mapper;


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
        restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found by Id " + id));
        restaurantRepository.deleteById(id);
    }

    @Transactional
    public CreatMealResponse createMeals(CreateMealRequest request, Long id) {
        RestaurantEntity restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found by Id " + id));

        List<MealEntity> mealEntities = request.getMeals().stream()
                .map(mealRequest -> new MealEntity()
                        .setName(mealRequest.getName())
                        .setPrice(mealRequest.getPrice())
                        .setRestaurant(restaurant)
                )
                .collect(Collectors.toList());

        mealRepository.deleteAllByRestaurantId(restaurant.getId());
        mealRepository.saveAll(mealEntities);

        restaurantRepository.getById(id).setMeals(mealEntities);
        restaurantRepository.save(restaurant);

        return new CreatMealResponse()
                .setRestaurantDto(mapper.mapToRestaurantDto(restaurant));
    }

}
