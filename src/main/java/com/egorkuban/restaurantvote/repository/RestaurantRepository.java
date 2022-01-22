package com.egorkuban.restaurantvote.repository;

import com.egorkuban.restaurantvote.jpa.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.meals")
    List<Restaurant> findAllByMeals();
}
