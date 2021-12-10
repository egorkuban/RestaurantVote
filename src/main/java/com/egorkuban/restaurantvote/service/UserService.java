package com.egorkuban.restaurantvote.service;

import com.egorkuban.restaurantvote.model.Restaurant;
import com.egorkuban.restaurantvote.model.Vote;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public List<Restaurant> getAllRestaurantsRequest() {
        return new ArrayList<>(AdminService.RESTAURANT_LIST);
    }

    public void votingRequest(Long id) {

    }
}
