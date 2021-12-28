package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.jpa.entity.RestaurantEntity;
import com.egorkuban.restaurantvote.jpa.entity.UserEntity;
import com.egorkuban.restaurantvote.jpa.entity.VoteEntity;
import com.egorkuban.restaurantvote.jpa.repository.RestaurantRepository;
import com.egorkuban.restaurantvote.jpa.repository.UserRepository;
import com.egorkuban.restaurantvote.jpa.repository.VoteRepository;
import com.egorkuban.restaurantvote.mapper.MealMapper;
import com.egorkuban.restaurantvote.mapper.RestaurantMapper;
import com.egorkuban.restaurantvote.model.response.VoteResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class UserServiceTest {
    UserService userService;
    RestaurantRepository restaurantRepository;
    VoteRepository voteRepository;
    UserRepository userRepository;
    RestaurantMapper restaurantMapper;
    MealMapper mealMapper;

    @BeforeEach
    public void init() {
        mealMapper = new MealMapper();
        restaurantMapper = new RestaurantMapper(mealMapper);
        restaurantRepository = mock(RestaurantRepository.class);
        voteRepository = mock(VoteRepository.class);
        userRepository = mock(UserRepository.class);
        userService = spy(new UserService(restaurantRepository, voteRepository, userRepository, restaurantMapper));
    }

    @Test
    void getAllRestaurantsTest() {
        when(restaurantRepository.getAllRestaurants()).thenReturn(anyList());
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

        VoteResponse response = userService.vote(2L, 1L);
        assertEquals(response.getRestaurantId(), 2L);

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

        VoteResponse response = userService.vote(1L, 1L);

        verify(voteRepository).save(argThat((VoteEntity vote ) -> vote.getUser().getId() == 1L));


        assertEquals(response.getRestaurantId(), 1L);

    }

}
