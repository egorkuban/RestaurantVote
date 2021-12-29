package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.jpa.entity.UserEntity;
import com.egorkuban.restaurantvote.jpa.entity.VoteEntity;
import com.egorkuban.restaurantvote.jpa.repository.RestaurantRepository;
import com.egorkuban.restaurantvote.jpa.repository.UserRepository;
import com.egorkuban.restaurantvote.jpa.repository.VoteRepository;
import com.egorkuban.restaurantvote.mapper.RestaurantMapper;
import com.egorkuban.restaurantvote.model.RestaurantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final static LocalTime TIME_EXPIRED_BORDER = LocalTime.of(11, 0);
    private final RestaurantRepository restaurantRepository;
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;

    public List<RestaurantDto> getAllRestaurants() {
        return restaurantRepository.getAllRestaurants().stream()
                .map(RestaurantMapper.RESTAURANT_INSTANT::mapToRestaurantDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void vote(Long restaurantId, Long userId) {
        VoteEntity resultVote = voteRepository.findByVoteDateAndUserId(LocalDate.now(), userId)
                .map(voteEntity -> changeVote(voteEntity, restaurantId))
                .orElse(createVote(restaurantId, userId));
        voteRepository.save(resultVote);

    }

    private VoteEntity changeVote(VoteEntity voteEntity, Long restaurantId) {
        if (isTimeExpired()) {
            throw new IllegalArgumentException("Current time is after " + TIME_EXPIRED_BORDER + ". You can't vote once more yet");
        }
        return voteEntity.setRestaurant(restaurantRepository.getById(restaurantId));
    }

    private VoteEntity createVote(Long restaurantId, Long userId) {
        return new VoteEntity()
                .setRestaurant(restaurantRepository.getById(restaurantId))
                .setUser(userRepository.getById(userId))
                .setVoteDate(LocalDate.now());
    }

    protected boolean isTimeExpired() {
        return !LocalTime.now().isBefore(TIME_EXPIRED_BORDER);
    }

    public Long getId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + authentication.getName() + " Not found"));
        return user.getId();
    }
}

