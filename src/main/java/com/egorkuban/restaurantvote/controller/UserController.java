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

    //
    //Юзер отправляет запрос - Ответ: список ресторанов
    //проверки в отдельный метод потом уберу
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
        //проверки в отдельный метод потом уберу
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime dateTimeVote = LocalDateTime.of(LocalDate.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(),
                10, 59, 59);
        return localDateTime.isBefore(dateTimeVote)
                ? new ResponseEntity<>(userService.vote(id), HttpStatus.ACCEPTED)
                : new ResponseEntity<>(userService.vote(id), HttpStatus.FORBIDDEN);

    }
}
