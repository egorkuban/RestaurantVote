package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.jpa.model.Meal;
import com.egorkuban.restaurantvote.jpa.model.Menu;
import com.egorkuban.restaurantvote.jpa.model.Restaurant;
import com.egorkuban.restaurantvote.repository.MealRepository;
import com.egorkuban.restaurantvote.repository.MenuRepository;
import com.egorkuban.restaurantvote.repository.RestaurantRepository;
import com.egorkuban.restaurantvote.to.MealDto;
import com.egorkuban.restaurantvote.to.request.CreateMealRequest;
import com.egorkuban.restaurantvote.to.request.CreateRestaurantRequest;
import com.egorkuban.restaurantvote.to.response.CreatMealResponse;
import com.egorkuban.restaurantvote.to.response.CreateRestaurantResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class AdminServiceTest {
    RestaurantService restaurantService;
    MealService mealService;
    RestaurantRepository restaurantRepository;
    MealRepository mealRepository;
    MenuRepository menuRepository;

    @BeforeEach
    public void init() {
        restaurantRepository = mock(RestaurantRepository.class);
        mealRepository = mock(MealRepository.class);
        menuRepository = mock(MenuRepository.class);
        restaurantService = new RestaurantService(restaurantRepository);
        mealService = new MealService(restaurantRepository,mealRepository,menuRepository);
    }

    @Test
    void createRestaurantTest() {
        CreateRestaurantRequest request = new CreateRestaurantRequest();
        request.setName("restaurant_1");
        request.setAddress("address_1");

        when(restaurantRepository.save(any(Restaurant.class)))
                .then((Answer<Restaurant>) invocationOnMock -> {
                    Restaurant arg = invocationOnMock.getArgument(0);
                    arg.setId(1L);
                    return arg;
                });

        CreateRestaurantResponse response = restaurantService.createRestaurant(request);

        verify(restaurantRepository, times(1)).save(any(Restaurant.class));
        assertEquals(response.getRestaurantDto().getId(), 1L);
        assertEquals(response.getRestaurantDto().getName(), "restaurant_1");
        assertEquals(response.getRestaurantDto().getAddress(), "address_1");
    }

    @Test
    void deleteRestaurantTest() {
        Restaurant restaurant = new Restaurant()
                .setId(1L);
        when(restaurantRepository.findById(eq(1L))).thenReturn(Optional.of(restaurant));
        restaurantService.deleteRestaurant(1L);
        verify(restaurantRepository, times(1)).delete(eq(restaurant));
    }

    @Test
    void createMealsTest() {
        List<MealDto> meals = new ArrayList<>(Arrays.asList(
                new MealDto()
                        .setName("MealName1")
                        .setPrice(BigDecimal.valueOf(100)),
                new MealDto()
                        .setName("MealName2")
                        .setPrice(BigDecimal.valueOf(150))
        ));

        CreateMealRequest request = new CreateMealRequest();
        request.setMeals(meals);

        Restaurant restaurant = new Restaurant()
                .setId(1L)
                .setName("restaurant_1")
                .setAddress("address_1");
        List<Meal> meal = new ArrayList<>(Arrays.asList(
                new Meal()
                        .setName("name")
                        .setPrice(BigDecimal.valueOf(20))
                        .setId(1L)));
        Menu menu = new Menu()
                        .setDate(LocalDate.now())
                        .setRestaurant(restaurant)
                        .setMeals(meal)
                        .setId(1L);
        restaurant.getMenu().add(menu);

        when(restaurantRepository.findById(eq(1L))).thenReturn(Optional.of(restaurant));


        CreatMealResponse response = mealService.createMenu(request, restaurant.getId());

        verify(restaurantRepository, times(1)).save(eq(restaurant));
        assertEquals(response.getMenuDto().getMeals().size(), 2);
        assertEquals(response.getMenuDto().getMeals().get(0).getName(), "MealName1");
    }
}