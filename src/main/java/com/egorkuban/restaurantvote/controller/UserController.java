package com.egorkuban.restaurantvote.controller;

import com.egorkuban.restaurantvote.model.RestaurantDto;
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
    public ResponseEntity<String> vote(@PathVariable Long id) {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime dateTimeVote = LocalDateTime.of(LocalDate.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(),
                20, 59, 59);
        if (localDateTime.isBefore(dateTimeVote)) {
            userService.vote(id);
            return new ResponseEntity<>("Your vote has been counted for the restaurant :" + id, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Sorry, your vote is not counted, voting time is over", HttpStatus.FORBIDDEN);
        }
    }
}
