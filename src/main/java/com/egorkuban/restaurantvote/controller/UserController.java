package com.egorkuban.restaurantvote.controller;

import com.egorkuban.restaurantvote.model.RestaurantDto;
import com.egorkuban.restaurantvote.model.response.VoteResponse;
import com.egorkuban.restaurantvote.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        final List<RestaurantDto> allRestaurantsWithMeals = userService.getAllRestaurants();
        return CollectionUtils.isEmpty(allRestaurantsWithMeals)
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(allRestaurantsWithMeals, HttpStatus.OK);
    }

    @PostMapping("/restaurants/{id}/vote")
    public ResponseEntity<VoteResponse> vote(@PathVariable Long id) {
       return new ResponseEntity<>(userService.vote(id,1L),HttpStatus.ACCEPTED);
    }
}
