package com.egorkuban.restaurantvote.controller;

import com.egorkuban.restaurantvote.service.MenuService;
import com.egorkuban.restaurantvote.to.DishDto;
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
public class MenuController {
    private final MenuService menuService;

    @PutMapping("/admin/restaurant/{restaurantId}/menu")
    public ResponseEntity<CreatMealResponse> createMenu(@RequestBody CreateMealRequest request,
                                                        @PathVariable Long restaurantId) {
        return new ResponseEntity<>(menuService.createMenu(request, restaurantId), HttpStatus.CREATED);
    }

    @GetMapping("/user/restaurant/{restaurantId}/menu")
    public List<DishDto> getMenu(@PathVariable Long restaurantId) {
        return menuService.getMenu(LocalDate.now(), restaurantId);
    }
}