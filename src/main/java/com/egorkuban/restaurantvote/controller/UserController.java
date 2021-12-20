package com.egorkuban.restaurantvote.controller;

import com.egorkuban.restaurantvote.model.RestaurantDto;
import com.egorkuban.restaurantvote.model.response.VoteResponse;
import com.egorkuban.restaurantvote.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest")
public class UserController {
    private final UserService userService;

    //Юзер отправляет запрос - Ответ: список ресторанов
    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        final List<RestaurantDto> allRestaurantsWithMeals = userService.getAllRestaurants();
        return allRestaurantsWithMeals != null && !allRestaurantsWithMeals.isEmpty()
                ? new ResponseEntity<>(allRestaurantsWithMeals, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Юзер отправляет Id ресторана - Ответ: id ресторана + дата
    @PostMapping("/restaurants/{id}/vote")
    public ResponseEntity<VoteResponse> vote(@PathVariable Long id) {
       return new ResponseEntity<VoteResponse>(userService.vote(id,1L),HttpStatus.ACCEPTED);
    }
}
