package com.egorkuban.restaurantvote.controller;

import com.egorkuban.restaurantvote.service.RestaurantService;
import com.egorkuban.restaurantvote.to.RestaurantDto;
import com.egorkuban.restaurantvote.to.request.CreateRestaurantRequest;
import com.egorkuban.restaurantvote.to.response.CreateRestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class RestaurantController {
    private final RestaurantService restaurantService;
    @PutMapping("/admin/restaurant")
    public ResponseEntity<CreateRestaurantResponse> createRestaurant(@RequestBody CreateRestaurantRequest request) {
        return new ResponseEntity<>(restaurantService.createRestaurant(request), HttpStatus.CREATED);
    }
    @DeleteMapping("/admin/restaurant/{restaurantId}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return new ResponseEntity<>("Restaurant with id = " + restaurantId + " removed", HttpStatus.OK);
    }
    @GetMapping("/user/restaurant/")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        final List<RestaurantDto> allRestaurantsWithMeals = restaurantService.getAllRestaurants();
        return CollectionUtils.isEmpty(allRestaurantsWithMeals)
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(allRestaurantsWithMeals, HttpStatus.OK);
    }

}
