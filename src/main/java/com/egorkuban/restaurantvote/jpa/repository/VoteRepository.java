package com.egorkuban.restaurantvote.jpa.repository;

import com.egorkuban.restaurantvote.jpa.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository extends JpaRepository<VoteEntity, Long> {
    List<VoteEntity> findAllByVoteDateAndUserEntityId(LocalDate voteDate, Long userEntity_id);

    void deleteVoteEntityByVoteDateAndUserEntityId(LocalDate voteDate, Long userEntity_id);

}
