package com.egorkuban.restaurantvote.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
public class VoteResponse {
    Long restaurantId;
    LocalDate voteDate;
}
