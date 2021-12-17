package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.jpa.entity.VoteEntity;
import com.egorkuban.restaurantvote.jpa.repository.RestaurantRepository;
import com.egorkuban.restaurantvote.jpa.repository.UserRepository;
import com.egorkuban.restaurantvote.jpa.repository.VoteRepository;
import com.egorkuban.restaurantvote.mapper.RestaurantMapper;
import com.egorkuban.restaurantvote.model.RestaurantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    public void vote(Long id) {
        LocalDate localDate = LocalDate.now();
        Long userId = 1L;

        VoteEntity vote = new VoteEntity()
                .setUserEntity(userRepository.getById(1L))
                .setRestaurant(restaurantRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Restaurant not found by Id " + id)))
                .setVoteDate(LocalDate.now());
        List<VoteEntity> voteEntities = voteRepository.findAllByVoteDateAndUserEntityId(localDate, userId);
        if (!voteEntities.isEmpty()) {
            voteRepository.deleteVoteEntityByVoteDateAndUserEntityId(localDate, userId);
        }
        voteRepository.save(vote);
    }

}
