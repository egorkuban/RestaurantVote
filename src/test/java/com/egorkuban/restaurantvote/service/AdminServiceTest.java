package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.jpa.entity.RestaurantEntity;
import com.egorkuban.restaurantvote.jpa.repository.RestaurantRepository;
import com.egorkuban.restaurantvote.mapper.MealMapper;
import com.egorkuban.restaurantvote.mapper.RestaurantMapper;
import com.egorkuban.restaurantvote.model.MealDto;
import com.egorkuban.restaurantvote.model.request.CreateMealRequest;
import com.egorkuban.restaurantvote.model.request.CreateRestaurantRequest;
import com.egorkuban.restaurantvote.model.response.CreatMealResponse;
import com.egorkuban.restaurantvote.model.response.CreateRestaurantResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class AdminServiceTest {
    AdminService adminService;
    RestaurantMapper restaurantMapper;
    MealMapper mealMapper;
    RestaurantRepository restaurantRepository;

    @BeforeEach
    public void init() {
        mealMapper = new MealMapper();
        restaurantMapper = new RestaurantMapper(mealMapper);
        restaurantRepository = mock(RestaurantRepository.class);
        adminService = new AdminService(restaurantRepository, restaurantMapper, mealMapper);
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
        RestaurantEntity entity = new RestaurantEntity()
                .setId(1L);
        when(restaurantRepository.findById(entity.getId())).thenReturn(Optional.of(entity)).thenReturn(Optional.empty());
        adminService.deleteRestaurant(1L);
        verify(restaurantRepository, times(1)).delete(any(RestaurantEntity.class));
    }

    @Test
    void createMealsTest() {
        List<MealDto> meals = List.of(
                new MealDto()
                        .setName("MealName1")
                        .setPrice(BigDecimal.valueOf(100)),
                new MealDto()
                        .setName("MealName1")
                        .setPrice(BigDecimal.valueOf(150))
        );
        CreateMealRequest request = new CreateMealRequest();
        request.setMeals(meals);

        RestaurantEntity entity = new RestaurantEntity()
                .setId(1L)
                .setName("restaurant_1")
                .setAddress("address_1")
                .setMeals(mealMapper.mapToMealsEntity(request.getMeals()));

        when(restaurantRepository.findById(entity.getId())).thenReturn(Optional.of(entity)).thenReturn(Optional.empty());


        CreatMealResponse response = adminService.createMeals(request, entity.getId());
        verify(restaurantRepository, times(1)).save(any(RestaurantEntity.class));
        assertEquals(response.getRestaurantDto().getId(), 1L);
        assertEquals(response.getRestaurantDto().getName(), "restaurant_1");
        assertEquals(response.getRestaurantDto().getAddress(), "address_1");
        assertNotNull(response.getRestaurantDto().getMeals());


    }
}