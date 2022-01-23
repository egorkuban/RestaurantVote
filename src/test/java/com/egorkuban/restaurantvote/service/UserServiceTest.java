package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.jpa.model.Restaurant;
import com.egorkuban.restaurantvote.jpa.model.User;
import com.egorkuban.restaurantvote.jpa.model.Vote;
import com.egorkuban.restaurantvote.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

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
