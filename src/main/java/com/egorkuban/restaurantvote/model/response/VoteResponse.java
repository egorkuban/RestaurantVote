package com.egorkuban.restaurantvote.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VoteResponse {
    Long restaurantId;
    LocalDateTime voteDate;
}
