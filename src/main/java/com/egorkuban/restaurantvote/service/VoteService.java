package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.exception.ChangeVoteException;
import com.egorkuban.restaurantvote.jpa.model.User;
import com.egorkuban.restaurantvote.jpa.model.Vote;
import com.egorkuban.restaurantvote.repository.RestaurantRepository;
import com.egorkuban.restaurantvote.repository.UserRepository;
import com.egorkuban.restaurantvote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class VoteService {
    private static final LocalTime TIME_EXPIRED_BORDER = LocalTime.of(11, 0);
    private final UserService userService;
    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Transactional
    public void vote(Long restaurantId) {
        User user = userService.getUser();;
        Vote resultVote = voteRepository.findByVoteDateAndUserId(LocalDate.now(), user.getId())
                .map(vote -> changeVote(vote, restaurantId))
                .orElse(createVote(restaurantId, user.getId()));
        voteRepository.save(resultVote);
    }

    private Vote changeVote(Vote vote, Long restaurantId) {
        if (isTimeExpired()) {
            throw new ChangeVoteException("Current time is after " + TIME_EXPIRED_BORDER + ". You can't vote once more yet");
        }
        return vote.setRestaurant(restaurantRepository.getById(restaurantId));
    }

    private Vote createVote(Long restaurantId, Long userId) {
        return new Vote()
                .setRestaurant(restaurantRepository.getById(restaurantId))
                .setUser(userRepository.getById(userId))
                .setVoteDate(LocalDate.now());
    }

    protected boolean isTimeExpired() {
        return !LocalTime.now().isBefore(TIME_EXPIRED_BORDER);
    }


}
