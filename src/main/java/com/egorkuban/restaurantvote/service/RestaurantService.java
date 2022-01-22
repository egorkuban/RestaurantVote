package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.jpa.model.Restaurant;
import com.egorkuban.restaurantvote.mapper.RestaurantMapper;
import com.egorkuban.restaurantvote.repository.RestaurantRepository;
import com.egorkuban.restaurantvote.to.RestaurantDto;
import com.egorkuban.restaurantvote.to.request.CreateRestaurantRequest;
import com.egorkuban.restaurantvote.to.response.CreateRestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public CreateRestaurantResponse createRestaurant(CreateRestaurantRequest request) {
        Restaurant restaurant = new Restaurant()
                .setName(request.getName())
                .setAddress(request.getAddress());
        restaurantRepository.save(restaurant);

        return new CreateRestaurantResponse()
                .setRestaurantDto(RestaurantMapper.INSTANCE.mapToRestaurantDto(restaurant));
    }

    @Transactional
    public void deleteRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found by Id " + id));
        restaurantRepository.delete(restaurant);
    }

    public List<RestaurantDto> getAllRestaurants() {
        return restaurantRepository.findAllByMeals().stream()
                .map(RestaurantMapper.INSTANCE::mapToRestaurantDto)
                .collect(Collectors.toList());
    }
}
