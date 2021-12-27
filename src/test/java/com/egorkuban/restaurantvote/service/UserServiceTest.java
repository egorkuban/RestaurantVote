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
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class UserServiceTest {
    final static LocalTime TIME_EXPIRED_BORDER = LocalTime.of(11, 0);
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
        userService = new UserService(restaurantRepository, voteRepository, userRepository, restaurantMapper);
    }

    @Test
    void getAllRestaurantsTest() {
        when(restaurantRepository.getAllRestaurants()).thenReturn(anyList());
    }

    @Test
    void voteTest() {

        RestaurantEntity restaurant = new RestaurantEntity()
                .setName("Name_Restaurant")
                .setId(1L);
        UserEntity user = new UserEntity();
        user.setEmail("Example@email.com");
        user.setId(1L);

        VoteEntity vote = new VoteEntity();
        vote.setVoteDate(LocalDate.now());
        vote.setRestaurant(restaurant);
        vote.setUser(user);

        when(voteRepository.findByVoteDateAndUserId(LocalDate.now(), 1L)).thenReturn(Optional.of(vote));
        if (LocalTime.now().isAfter(TIME_EXPIRED_BORDER)) {
            assertThrows(IllegalArgumentException.class, () -> {
                userService.vote(vote.getRestaurant().getId(), vote.getUser().getId());
            });
        } else {
            VoteResponse response = userService.vote(vote.getRestaurant().getId(), vote.getUser().getId());
            assertEquals(response.getRestaurantId(), 1L);
            assertEquals(response.getVoteDate(), LocalDate.now());
            verify(voteRepository, times(1)).save(eq(vote));
        }
    }
}