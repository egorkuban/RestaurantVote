package com.egorkuban.restaurantvote.repository;

import com.egorkuban.restaurantvote.jpa.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
