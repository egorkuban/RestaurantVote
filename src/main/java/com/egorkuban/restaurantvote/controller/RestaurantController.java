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
    @PostMapping("/admin/restaurants/new")
    public ResponseEntity<CreateRestaurantResponse> createRestaurant(@RequestBody CreateRestaurantRequest request) {

        return new ResponseEntity<>(restaurantService.createRestaurant(request), HttpStatus.CREATED);
    }
    @DeleteMapping("/admin/restaurants/{id}/delete")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>("Restaurant with id = " + id + " removed", HttpStatus.OK);
    }
    @GetMapping("/user/restaurants")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        final List<RestaurantDto> allRestaurantsWithMeals = restaurantService.getAllRestaurants();
        return CollectionUtils.isEmpty(allRestaurantsWithMeals)
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(allRestaurantsWithMeals, HttpStatus.OK);
    }

}
