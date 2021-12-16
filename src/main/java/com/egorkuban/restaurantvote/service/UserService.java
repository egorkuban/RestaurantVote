package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.jpa.entity.RestaurantEntity;
import com.egorkuban.restaurantvote.jpa.entity.VoteEntity;
import com.egorkuban.restaurantvote.jpa.repository.RestaurantRepository;
import com.egorkuban.restaurantvote.jpa.repository.UserRepository;
import com.egorkuban.restaurantvote.jpa.repository.VoteRepository;
import com.egorkuban.restaurantvote.model.response.VoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RestaurantRepository restaurantRepository;
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;

    public List<RestaurantEntity> getAllRestaurants() {
        return restaurantRepository
                .findAll()
                .stream().toList();
    }

    public VoteResponse vote(Long id) {
        VoteEntity vote = new VoteEntity()
                .setVoteDate(LocalDateTime.now())
                .setRestaurant(restaurantRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Restaurant not found by Id " + id)))
                .setUserEntity(null);
        voteRepository.save(vote);
        return new VoteResponse()
                .setRestaurantId(restaurantRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Restaurant not found by Id " + id))
                        .getId())
                .setVoteDate(LocalDateTime.now());
    }


}
