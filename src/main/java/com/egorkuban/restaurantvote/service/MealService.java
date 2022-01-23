package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.jpa.model.Meal;
import com.egorkuban.restaurantvote.jpa.model.Menu;
import com.egorkuban.restaurantvote.jpa.model.Restaurant;
import com.egorkuban.restaurantvote.mapper.MealMapper;
import com.egorkuban.restaurantvote.mapper.MenuMapper;
import com.egorkuban.restaurantvote.repository.MealRepository;
import com.egorkuban.restaurantvote.repository.MenuRepository;
import com.egorkuban.restaurantvote.repository.RestaurantRepository;
import com.egorkuban.restaurantvote.to.MealDto;
import com.egorkuban.restaurantvote.to.request.CreateMealRequest;
import com.egorkuban.restaurantvote.to.response.CreatMealResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor

public class MealService {

    private final RestaurantRepository restaurantRepository;
    private final MealRepository mealRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public CreatMealResponse createMenu(CreateMealRequest request, Long id) {
        List<Meal> meals = MealMapper.INSTANCE.mapToMeals(request.getMeals());
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found by Id " + id));
       Menu menu = menuRepository.findByRestaurantIdAndIsActual(id)
               .map(menu1 -> {
                   menu1.setIsActual(false);
                   return createNewMenu(meals,restaurant);
               })
               .orElse(createNewMenu(meals,restaurant));

       restaurant.getMenu().add(menu);
       menuRepository.save(menu);
       restaurantRepository.save(restaurant);



        return new CreatMealResponse()
                .setMenuDto(MenuMapper.INSTANCE.mapToMenuDto(request.getMeals()));
    }
    private Menu createNewMenu(List<Meal> meals, Restaurant restaurant){
        return MenuMapper.INSTANCE.mapToMenu(meals,restaurant);
    }
    @Transactional
    public List<MealDto> getMenu(LocalDate date, int restaurantId){
        Restaurant restaurant = restaurantRepository.getById((long) restaurantId);

        return mealRepository.findAllByDateAndMenu_Restaurant(date, restaurant)
                .stream().map(meal -> {
                    MealDto mealTo = new MealDto();
                    mealTo.setName(meal.getName());
                    mealTo.setPrice(meal.getPrice());
                    return mealTo;
                }).toList();
    }
}
