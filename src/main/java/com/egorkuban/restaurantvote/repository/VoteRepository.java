package com.egorkuban.restaurantvote.repository;

import com.egorkuban.restaurantvote.jpa.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByVoteDateAndUserId(LocalDate voteDate, Long userId);
}
