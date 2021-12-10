package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.model.RestaurantDto;
import com.egorkuban.restaurantvote.model.request.VoteRequest;
import com.egorkuban.restaurantvote.model.response.VoteResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public List<RestaurantDto> getAllRestaurants() {
        return new ArrayList<>(AdminService.RESTAURANTS_WITH_MEALS_LIST);
    }

    public VoteResponse vote(VoteRequest request) {
        return null;
    }
}
