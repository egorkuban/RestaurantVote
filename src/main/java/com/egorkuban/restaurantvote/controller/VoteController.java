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

    @PostMapping("/user/restaurant/{restaurantId}/vote")
    public ResponseEntity<?> vote(@PathVariable Long restaurantId) {
        voteService.vote(restaurantId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
