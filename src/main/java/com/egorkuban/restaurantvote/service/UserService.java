package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.jpa.entity.VoteEntity;
import com.egorkuban.restaurantvote.jpa.repository.RestaurantRepository;
import com.egorkuban.restaurantvote.jpa.repository.UserRepository;
import com.egorkuban.restaurantvote.jpa.repository.VoteRepository;
import com.egorkuban.restaurantvote.mapper.RestaurantMapper;
import com.egorkuban.restaurantvote.model.RestaurantDto;
import com.egorkuban.restaurantvote.model.response.VoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RestaurantRepository restaurantRepository;
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantMapper mapper;


    public List<RestaurantDto> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(mapper::mapToRestaurantDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public VoteResponse vote(Long id, Long userId) {
        LocalDateTime timeVote = LocalDateTime.now();
        VoteEntity vote = voteRepository.findByVoteDateAndUserEntityId(timeVote.toLocalDate(), userId);
        if (timeVote.isBefore(LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 59, 59)))) {
            if (vote != null) {
                vote.setRestaurant(restaurantRepository.getById(id));
            } else {
                vote = new VoteEntity()
                        .setUserEntity(userRepository.getById(userId))
                        .setRestaurant(restaurantRepository.getById(id))
                        .setVoteDate(timeVote.toLocalDate());
            }
            voteRepository.save(vote);
        } else if (timeVote.isAfter(LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 59, 59)))) {
            if (vote == null) {
                vote = new VoteEntity()
                        .setUserEntity(userRepository.getById(userId))
                        .setRestaurant(restaurantRepository.getById(id))
                        .setVoteDate(timeVote.toLocalDate());
                voteRepository.save(vote);
            }
        }
        return new VoteResponse()
                .setRestaurantId(vote.getRestaurant().getId())
                .setVoteDate(vote.getVoteDate());
    }

}
