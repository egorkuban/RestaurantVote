package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.jpa.model.Menu;
import com.egorkuban.restaurantvote.jpa.model.Restaurant;
import com.egorkuban.restaurantvote.mapper.DishMapper;
import com.egorkuban.restaurantvote.mapper.MenuMapper;
import com.egorkuban.restaurantvote.repository.MenuRepository;
import com.egorkuban.restaurantvote.repository.RestaurantRepository;
import com.egorkuban.restaurantvote.to.DishDto;
import com.egorkuban.restaurantvote.to.request.CreateMealRequest;
import com.egorkuban.restaurantvote.to.response.CreatMealResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor

public class MenuService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public CreatMealResponse createMenu(CreateMealRequest request, Long restaurantId) {
        Restaurant restaurant = restaurantRepository.getById(restaurantId);
        menuRepository.findActualMenu(restaurant,request.getMenuDate())
                .ifPresent(menu -> menu.setIsActual(Boolean.FALSE));
        Menu newActualMenu = new Menu()
                .setIsActual(true)
                .setDate(request.getMenuDate())
                .setRestaurant(restaurant)
                .setDishes(DishMapper.INSTANCE.mapToEntity(request.getDishes()));
        newActualMenu.getDishes().forEach(dish -> dish.setMenu(newActualMenu));
        menuRepository.save(newActualMenu);
        return new CreatMealResponse()
                .setMenuDto(MenuMapper.INSTANCE.mapToMenuDto(request.getDishes()));
    }

    @Transactional
    public List<DishDto> getMenu(LocalDate date, long restaurantId) {
        Restaurant restaurant = restaurantRepository.getById(restaurantId);
        return menuRepository.findActualMenu(restaurant,date)
                .map(Menu::getDishes)
                .map(DishMapper.INSTANCE::mapToDto)
                .orElse(Collections.emptyList());
    }
}