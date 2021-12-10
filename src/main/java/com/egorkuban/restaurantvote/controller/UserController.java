package com.egorkuban.restaurantvote.controller;

import com.egorkuban.restaurantvote.model.Restaurant;
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
    //
    //Юзер отправляет запрос - Ответ: список ресторанов
    @GetMapping("/restaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurantsResponse() {
        final List<Restaurant> allRestaurants = userService.getAllRestaurantsRequest();
        return allRestaurants != null && !allRestaurants.isEmpty()
                ? new ResponseEntity<>(allRestaurants, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //Юзер отправляет Id ресторана - Ответ: id ресторана + дата
    @PostMapping("/restaurants/{id}/vote")
    public ResponseEntity<String> votingResponse(@PathVariable Long id) {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime dateTimeVote = LocalDateTime.of(LocalDate.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(),
                10, 59, 59);
        return localDateTime.isBefore(dateTimeVote)
                ? new ResponseEntity<>("Thank you, your vote has been counted for the restaurant " +
                " " + localDateTime, HttpStatus.OK)
                : new ResponseEntity<>("Your vote is not counted, time" + localDateTime.toLocalTime(), HttpStatus.OK);

    }
}
