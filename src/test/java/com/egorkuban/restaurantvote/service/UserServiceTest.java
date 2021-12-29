package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.jpa.entity.MealEntity;
import com.egorkuban.restaurantvote.jpa.entity.RestaurantEntity;
import com.egorkuban.restaurantvote.jpa.entity.UserEntity;
import com.egorkuban.restaurantvote.jpa.entity.VoteEntity;
import com.egorkuban.restaurantvote.jpa.repository.RestaurantRepository;
import com.egorkuban.restaurantvote.jpa.repository.UserRepository;
import com.egorkuban.restaurantvote.jpa.repository.VoteRepository;
import com.egorkuban.restaurantvote.model.RestaurantDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceTest {
    UserService userService;
    RestaurantRepository restaurantRepository;
    VoteRepository voteRepository;
    UserRepository userRepository;

    @BeforeEach
    public void init() {
        restaurantRepository = mock(RestaurantRepository.class);
        voteRepository = mock(VoteRepository.class);
        userRepository = mock(UserRepository.class);
        userService = spy(new UserService(restaurantRepository, voteRepository, userRepository));
    }

    @Test
    void getAllRestaurantsTest() {
        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setName("NameRestaurant");
        restaurant.setAddress("AddressRestaurant");
        restaurant.setId(1L);

        List<MealEntity> meals = new ArrayList<>(Arrays.asList(
                new MealEntity()
                        .setName("mealName")
                        .setRestaurant(restaurant)
                        .setId(1L)
                        .setPrice(BigDecimal.valueOf(500)),
                new MealEntity()
                        .setName("mealName1")
                        .setRestaurant(restaurant)
                        .setId(2L)
                        .setPrice(BigDecimal.valueOf(400))
        ));

        restaurant.setMeals(meals);

        List<RestaurantEntity> restaurantsList = new ArrayList<>();
        restaurantsList.add(restaurant);

        when(restaurantRepository.getAllRestaurants()).thenReturn(restaurantsList);

        List<RestaurantDto> restaurants = userService.getAllRestaurants();

        assertEquals(restaurants.size(),1);
        assertEquals(restaurants.get(0).getName(),"NameRestaurant");
        assertEquals(restaurants.get(0).getId(),1L);
        assertEquals(restaurants.get(0).getMeals().size(),2);
        assertEquals(restaurants.get(0).getMeals().get(0).getName(),"mealName");
        assertEquals(restaurants.get(0).getMeals().get(0).getPrice(),BigDecimal.valueOf(500));
    }

    @Test
    void voteTest() {

        RestaurantEntity oldVoteRestaurant = new RestaurantEntity()
                .setName("Name_Restaurant")
                .setId(1L);

        UserEntity user = new UserEntity();
        user.setEmail("Example@email.com");
        user.setId(1L);

        VoteEntity vote = new VoteEntity();
        vote.setVoteDate(LocalDate.now());
        vote.setRestaurant(oldVoteRestaurant);
        vote.setUser(user);

        RestaurantEntity newVoteRestaurant = new RestaurantEntity()
                .setName("New_Restaurant")
                .setId(2L);

        when(voteRepository.findByVoteDateAndUserId(LocalDate.now(), 1L)).thenReturn(Optional.of(vote));
        when(restaurantRepository.getById(eq(2L))).thenReturn(newVoteRestaurant);
        when(userService.isTimeExpired()).thenReturn(false);

        userService.vote(2L, 1L);

        verify(voteRepository).save(argThat((VoteEntity v ) -> v.getUser().getId() == 1L));

    }

    @Test
    void voteTestFailed() {
        RestaurantEntity oldVoteRestaurant = new RestaurantEntity()
                .setName("Name_Restaurant")
                .setId(1L);

        UserEntity user = new UserEntity();
        user.setEmail("Example@email.com");
        user.setId(1L);

        VoteEntity vote = new VoteEntity();
        vote.setVoteDate(LocalDate.now());
        vote.setRestaurant(oldVoteRestaurant);
        vote.setUser(user);

        RestaurantEntity newVoteRestaurant = new RestaurantEntity()
                .setName("New_Restaurant")
                .setId(2L);

        when(voteRepository.findByVoteDateAndUserId(LocalDate.now(), 1L)).thenReturn(Optional.of(vote));
        when(restaurantRepository.getById(eq(2L))).thenReturn(newVoteRestaurant);
        when(userService.isTimeExpired()).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> {
            userService.vote(vote.getRestaurant().getId(), vote.getUser().getId());
        });
    }

    @Test
    void voteTestNewVote() {
        UserEntity user = new UserEntity();
        user.setEmail("Example@email.com");
        user.setId(1L);

        RestaurantEntity restaurant = new RestaurantEntity()
                .setName("Name_Restaurant")
                .setId(1L);

        when(restaurantRepository.getById(eq(1L))).thenReturn(restaurant);
        when(userRepository.getById(eq(1L))).thenReturn(user);

        userService.vote(1L, 1L);

        verify(voteRepository).save(argThat((VoteEntity vote ) -> vote.getUser().getId() == 1L));
    }

}
