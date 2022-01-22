package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.exception.ChangeVoteException;
import com.egorkuban.restaurantvote.jpa.model.User;
import com.egorkuban.restaurantvote.jpa.model.Vote;
import com.egorkuban.restaurantvote.repository.RestaurantRepository;
import com.egorkuban.restaurantvote.repository.UserRepository;
import com.egorkuban.restaurantvote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class VoteService {
    private static final LocalTime TIME_EXPIRED_BORDER = LocalTime.of(11, 0);
    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Transactional
    public void vote(Long restaurantId, Long userId) {
        Vote resultVote = voteRepository.findByVoteDateAndUserId(LocalDate.now(), userId)
                .map(vote -> {
                    try {
                        return changeVote(vote, restaurantId);
                    } catch (ChangeVoteException e) {
                        e.printStackTrace();
                    }
                    return vote;
                })
                .orElse(createVote(restaurantId, userId));
        voteRepository.save(resultVote);
    }

    private Vote changeVote(Vote vote, Long restaurantId) throws ChangeVoteException {
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

    public Long getVotingUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + authentication.getName() + " Not found"));
        return user.getId();
    }
}
