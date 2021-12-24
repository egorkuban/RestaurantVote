package com.egorkuban.restaurantvote.controller;

import com.egorkuban.restaurantvote.model.request.CreateMealRequest;
import com.egorkuban.restaurantvote.model.request.CreateRestaurantRequest;
import com.egorkuban.restaurantvote.model.response.CreatMealResponse;
import com.egorkuban.restaurantvote.model.response.CreateRestaurantResponse;
import com.egorkuban.restaurantvote.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/create")
    public ResponseEntity<CreateRestaurantResponse> createRestaurant(@RequestBody CreateRestaurantRequest request) {

        return new ResponseEntity<>(adminService.createRestaurant(request), HttpStatus.CREATED);
    }

    @PostMapping("/restaurants/{id}/delete")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Long id) {
        adminService.deleteRestaurant(id);
        return new ResponseEntity<>("Restaurant with id = " + id + " removed", HttpStatus.OK);
    }

    @PostMapping("/restaurants/{id}/meals")
    public ResponseEntity<CreatMealResponse> createMeals(@RequestBody CreateMealRequest request, @PathVariable Long id) {
        return new ResponseEntity<>(adminService.createMeals(request, id), HttpStatus.CREATED);
    }

}
