package com.egorkuban.restaurantvote.controller;

import com.egorkuban.restaurantvote.model.RestaurantDto;
import com.egorkuban.restaurantvote.model.request.CreateMealRequest;
import com.egorkuban.restaurantvote.model.request.CreateRestaurantRequest;
import com.egorkuban.restaurantvote.model.response.CreateRestaurantResponse;
import com.egorkuban.restaurantvote.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest")
public class AdminController {

    private final AdminService adminService;

    ///Админ отправляет Имя + Адрес - Ответ Ресторан : Id, имя, адрес
    @PostMapping("/create")
    public ResponseEntity<CreateRestaurantResponse> createRestaurant(@RequestBody CreateRestaurantRequest request) {
        return new ResponseEntity<>(adminService.createRestaurant(request), HttpStatus.CREATED);
    }

    //Админ отправляет id ресторана - Ответ: id + статус
    @PostMapping("/restaurants/{id}/delete")
    public ResponseEntity<Long> deleteRestaurant(@PathVariable Long id) {
        return new ResponseEntity<>(adminService.deleteRestaurant(id), HttpStatus.OK);
    }

    //Админ отправляет Список еды + id ресторана - Ответ: Ресторан + список еды
    @PostMapping("/restaurants/{id}/meals")
    public ResponseEntity<RestaurantDto> createMealsList(@RequestBody CreateMealRequest request, @PathVariable Long id) {
        return new ResponseEntity<>(adminService.createRestaurantDto(request, id), HttpStatus.CREATED);
    }

}
