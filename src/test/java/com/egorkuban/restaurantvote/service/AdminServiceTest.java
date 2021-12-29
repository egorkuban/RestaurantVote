package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.jpa.entity.MealEntity;
import com.egorkuban.restaurantvote.jpa.entity.RestaurantEntity;
import com.egorkuban.restaurantvote.jpa.repository.RestaurantRepository;
import com.egorkuban.restaurantvote.model.MealDto;
import com.egorkuban.restaurantvote.model.request.CreateMealRequest;
import com.egorkuban.restaurantvote.model.request.CreateRestaurantRequest;
import com.egorkuban.restaurantvote.model.response.CreatMealResponse;
import com.egorkuban.restaurantvote.model.response.CreateRestaurantResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class AdminServiceTest {
    AdminService adminService;
    RestaurantRepository restaurantRepository;

    @BeforeEach
    public void init() {
        restaurantRepository = mock(RestaurantRepository.class);
        adminService = new AdminService(restaurantRepository);
    }

    @Test
    void createRestaurantTest() {
        //prepare data
        CreateRestaurantRequest request = new CreateRestaurantRequest();
        request.setName("restaurant_1");
        request.setAddress("address_1");

        //prepare mocks
        when(restaurantRepository.save(any(RestaurantEntity.class)))
                .then((Answer<RestaurantEntity>) invocationOnMock -> {
                    RestaurantEntity arg = invocationOnMock.getArgument(0);
                    arg.setId(1L);
                    return arg;
                });

        //invoke
        CreateRestaurantResponse response = adminService.createRestaurant(request);

        //verify
        verify(restaurantRepository, times(1)).save(any(RestaurantEntity.class));
        assertEquals(response.getRestaurantDto().getId(), 1L);
        assertEquals(response.getRestaurantDto().getName(), "restaurant_1");
        assertEquals(response.getRestaurantDto().getAddress(), "address_1");
    }

    @Test
    void deleteRestaurantTest() {
        RestaurantEntity restaurant = new RestaurantEntity()
                .setId(1L);
        when(restaurantRepository.findById(eq(1L))).thenReturn(Optional.of(restaurant));
        adminService.deleteRestaurant(1L);
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

        RestaurantEntity restaurant = new RestaurantEntity()
                .setId(1L)
                .setName("restaurant_1")
                .setAddress("address_1");
        List<MealEntity> meal = new ArrayList<>(Arrays.asList(
                new MealEntity()
                        .setName("name")
                        .setPrice(BigDecimal.valueOf(20))
                        .setId(1L)
                        .setRestaurant(restaurant)
        ));
        restaurant.setMeals(meal);

        when(restaurantRepository.findById(eq(1L))).thenReturn(Optional.of(restaurant));


        CreatMealResponse response = adminService.createMeals(request, restaurant.getId());

        verify(restaurantRepository, times(1)).save(eq(restaurant));
        assertEquals(response.getRestaurantDto().getId(), 1L);
        assertEquals(response.getRestaurantDto().getName(), "restaurant_1");
        assertEquals(response.getRestaurantDto().getAddress(), "address_1");
        assertEquals(response.getRestaurantDto().getMeals().size(), 2);
        assertEquals(response.getRestaurantDto().getMeals().get(0).getName(), "MealName1");


    }
}