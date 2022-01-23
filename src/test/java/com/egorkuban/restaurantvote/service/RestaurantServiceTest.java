package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.jpa.model.Dish;
import com.egorkuban.restaurantvote.jpa.model.Menu;
import com.egorkuban.restaurantvote.jpa.model.Restaurant;
import com.egorkuban.restaurantvote.repository.MealRepository;
import com.egorkuban.restaurantvote.repository.MenuRepository;
import com.egorkuban.restaurantvote.repository.RestaurantRepository;
import com.egorkuban.restaurantvote.to.RestaurantDto;
import com.egorkuban.restaurantvote.to.request.CreateRestaurantRequest;
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


class RestaurantServiceTest {
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
        mealService = new MealService(restaurantRepository,menuRepository);
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
    void getAllRestaurantsTest() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("NameRestaurant");
        restaurant.setAddress("AddressRestaurant");
        restaurant.setId(1L);

        List<Dish> dishes = new ArrayList<>(Arrays.asList(
                new Dish()
                        .setName("mealName")
                        .setId(1L)
                        .setPrice(BigDecimal.valueOf(500)),
                new Dish()
                        .setName("mealName1")
                        .setId(2L)
                        .setPrice(BigDecimal.valueOf(400))
        ));
        List<Menu> menus = new ArrayList<>();
        Menu menu = new Menu();
        menu.setRestaurant(restaurant);
        menu.setDate(LocalDate.now());
        menu.setDishes(dishes);
        menus.add(menu);


        restaurant.setDishes(menus);

        List<Restaurant> restaurantsList = new ArrayList<>();
        restaurantsList.add(restaurant);

        when(restaurantRepository.findAll()).thenReturn(restaurantsList);

        List<RestaurantDto> restaurants = restaurantService.getAllRestaurants();

        assertEquals(restaurants.size(),1);
        assertEquals(restaurants.get(0).getName(),"NameRestaurant");
        assertEquals(restaurants.get(0).getId(),1L);
    }


}