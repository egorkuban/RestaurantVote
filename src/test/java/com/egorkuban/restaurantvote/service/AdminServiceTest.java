package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.jpa.entity.RestaurantEntity;
import com.egorkuban.restaurantvote.jpa.repository.RestaurantRepository;
import com.egorkuban.restaurantvote.mapper.RestaurantMapper;
import com.egorkuban.restaurantvote.model.request.CreateRestaurantRequest;
import com.egorkuban.restaurantvote.model.response.CreateRestaurantResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class AdminServiceTest {
    @Autowired
    AdminService adminService;
    @MockBean
    RestaurantRepository restaurantRepository;
    @MockBean
    RestaurantMapper mapper;


    @Test
    void createRestaurantTest() {
        CreateRestaurantRequest request = new CreateRestaurantRequest();
        request.setName(anyString());
        request.setAddress(anyString());

        CreateRestaurantResponse response = adminService.createRestaurant(request);
        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setName(request.getName());
        restaurant.setAddress(request.getAddress());
        when(restaurantRepository.save(restaurant)).thenReturn(restaurantRepository.getById(restaurant.getId()));
        when(mapper.mapToRestaurantDto(restaurant)).thenReturn(response.getRestaurantDto());
        assertInstanceOf(CreateRestaurantResponse.class, response);
        

    }

    @Test
    void deleteRestaurant() {
    }

    @Test
    void createMeals() {
    }
}