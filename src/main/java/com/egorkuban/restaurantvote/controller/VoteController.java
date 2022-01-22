package com.egorkuban.restaurantvote.controller;

import com.egorkuban.restaurantvote.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class VoteController {
    private final VoteService voteService;

    @PostMapping("/user/restaurants/{id}/vote")
    public ResponseEntity vote(@PathVariable Long id) {
        voteService.vote(id, voteService.getVotingUserId());
        return new ResponseEntity(HttpStatus.OK);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity IllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }
}
