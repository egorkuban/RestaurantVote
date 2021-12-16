package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.jpa.entity.RestaurantEntity;
import com.egorkuban.restaurantvote.jpa.repository.RestaurantRepository;
import com.egorkuban.restaurantvote.jpa.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RestaurantRepository restaurantRepository;
    private final VoteRepository voteRepository;

    public List<RestaurantEntity> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

}
