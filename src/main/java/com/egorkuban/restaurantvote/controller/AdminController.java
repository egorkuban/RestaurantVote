package com.egorkuban.restaurantvote.controller;

import com.egorkuban.restaurantvote.model.Restaurant;
import com.egorkuban.restaurantvote.model.dto.MealTo;
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
    public ResponseEntity<String> createRestaurantToResponse(@RequestBody Restaurant restaurant) {
        adminService.createRestaurantRequest(restaurant);
        return new ResponseEntity<>("Restaurant: " + restaurant.getName() + "\n"
                + "Address: " + restaurant.getAddress() + "\n"
                + "Id: " + restaurant.getId(), HttpStatus.CREATED);
    }

    //Админ отправляет id ресторана - Ответ: id + статус
    @PostMapping("/restaurants/{id}/delete")
    public ResponseEntity<String> deleteRestaurantResponse(@PathVariable Long id) {
        adminService.deleteRestaurantRequest(id);
        return new ResponseEntity<>("Restaurant " + id + " removed from the list", HttpStatus.OK);
    }
    //Админ отправляет Список еды + id ресторана - Ответ: Ресторан + список еды
    @PostMapping("/restaurants/{id}/meals") //Я вижу такую логику, поступает еда для ресторана
    // это MealTo, из нее я достаю стоимость и цену и кладу в список, который отправляется в Menu
    public ResponseEntity<String> createMealsResponse(@RequestBody MealTo mealTo, @PathVariable int id) {
        adminService.createMealsRequest(mealTo, id);
        return new ResponseEntity<>("Я хз", HttpStatus.OK);
    }

}
