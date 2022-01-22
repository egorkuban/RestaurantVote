package com.egorkuban.restaurantvote.controller;

import com.egorkuban.restaurantvote.service.MealService;
import com.egorkuban.restaurantvote.to.MealDto;
import com.egorkuban.restaurantvote.to.request.CreateMealRequest;
import com.egorkuban.restaurantvote.to.response.CreatMealResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MealController {
    private final MealService mealService;

    @PutMapping("/admin/restaurants/{id}/menu/new")
    public ResponseEntity<CreatMealResponse> createMenu(@RequestBody CreateMealRequest request, @PathVariable Long id) {
        return new ResponseEntity<>(mealService.createMenu(request, id), HttpStatus.CREATED);
    }

    @GetMapping("/user/restaurants/{id}/menu")
    public List<MealDto> getMenu(@PathVariable int id) {
        return mealService.getMenu(LocalDate.now(), id);
    }
}
