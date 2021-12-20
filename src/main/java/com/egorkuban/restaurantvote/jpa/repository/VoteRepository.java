package com.egorkuban.restaurantvote.jpa.repository;

import com.egorkuban.restaurantvote.jpa.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository extends JpaRepository<VoteEntity, Long> {
    VoteEntity findByVoteDateAndUserEntityId(LocalDate voteDate, Long userEntity_id);
}
