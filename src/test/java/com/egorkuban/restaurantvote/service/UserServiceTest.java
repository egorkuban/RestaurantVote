package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.jpa.model.*;
import com.egorkuban.restaurantvote.repository.*;
import com.egorkuban.restaurantvote.to.RestaurantDto;
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
    RestaurantService restaurantService;
    MealService mealService;
    VoteService voteService;
    RestaurantRepository restaurantRepository;
    MealRepository mealRepository;
    VoteRepository voteRepository;
    UserRepository userRepository;
    MenuRepository menuRepository;
    @BeforeEach
    public void init() {
        restaurantRepository = mock(RestaurantRepository.class);
        voteRepository = mock(VoteRepository.class);
        userRepository = mock(UserRepository.class);
        mealRepository = mock(MealRepository.class);
        menuRepository = mock(MenuRepository.class);
        voteService = spy(new VoteService(voteRepository,restaurantRepository,userRepository));
        restaurantService = new RestaurantService(restaurantRepository);
        mealService = new MealService(restaurantRepository,menuRepository);
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

    @Test
    void voteTest() {

        Restaurant oldVoteRestaurant = new Restaurant()
                .setName("Name_Restaurant")
                .setId(1L);

        User user = new User();
        user.setEmail("Example@email.com");
        user.setId(1L);

        Vote vote = new Vote();
        vote.setVoteDate(LocalDate.now());
        vote.setRestaurant(oldVoteRestaurant);
        vote.setUser(user);

        Restaurant newVoteRestaurant = new Restaurant()
                .setName("New_Restaurant")
                .setId(2L);

        when(voteRepository.findByVoteDateAndUserId(LocalDate.now(), 1L)).thenReturn(Optional.of(vote));
        when(restaurantRepository.getById(eq(2L))).thenReturn(newVoteRestaurant);
        when(voteService.isTimeExpired()).thenReturn(false);

        voteService.vote(2L, 1L);

        verify(voteRepository).save(argThat((Vote v ) -> v.getUser().getId() == 1L));
    }

    @Test
    void voteTestFailed() {
        Restaurant oldVoteRestaurant = new Restaurant()
                .setName("Name_Restaurant")
                .setId(1L);

        User user = new User();
        user.setEmail("Example@email.com");
        user.setId(1L);

        Vote vote = new Vote();
        vote.setVoteDate(LocalDate.now());
        vote.setRestaurant(oldVoteRestaurant);
        vote.setUser(user);

        Restaurant newVoteRestaurant = new Restaurant()
                .setName("New_Restaurant")
                .setId(2L);

        when(voteRepository.findByVoteDateAndUserId(LocalDate.now(), 1L)).thenReturn(Optional.of(vote));
        when(restaurantRepository.getById(eq(2L))).thenReturn(newVoteRestaurant);
        when(voteService.isTimeExpired()).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () ->
                voteService.vote(vote.getRestaurant().getId(), vote.getUser().getId())
        );
    }

    @Test
    void voteTestNewVote() {
        User user = new User();
        user.setEmail("Example@email.com");
        user.setId(1L);

        Restaurant restaurant = new Restaurant()
                .setName("Name_Restaurant")
                .setId(1L);

        when(restaurantRepository.getById(eq(1L))).thenReturn(restaurant);
        when(userRepository.getById(eq(1L))).thenReturn(user);

        voteService.vote(1L, 1L);

        verify(voteRepository).save(argThat((Vote vote ) -> vote.getUser().getId() == 1L));
    }
}
