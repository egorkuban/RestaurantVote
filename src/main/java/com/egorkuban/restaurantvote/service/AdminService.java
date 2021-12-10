package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.model.Meal;
import com.egorkuban.restaurantvote.model.Restaurant;
import com.egorkuban.restaurantvote.model.dto.MealTo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AdminService {

    static final List<Restaurant> RESTAURANT_LIST = new ArrayList<>();

    private static final AtomicLong RESTAURANT_ID = new AtomicLong(100000);


    @Transactional
    public void createRestaurantRequest(Restaurant restaurant) {
        final long restaurantId = RESTAURANT_ID.incrementAndGet();
        restaurant.setId(restaurantId);
        RESTAURANT_LIST.add(restaurant);

    }

    @Transactional
    public List<Meal> createMealsRequest (MealTo mealTo, int id) {
        return null;
    }


    @Transactional
    public void deleteRestaurantRequest(Long id) {
        RESTAURANT_LIST.removeIf(i -> i.getId().equals(id));
    }
}
