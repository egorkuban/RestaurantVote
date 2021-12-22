package com.egorkuban.restaurantvote.jpa.repository;

import com.egorkuban.restaurantvote.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    @Query("SELECT r FROM RestaurantEntity r join fetch r.meals")
    List<RestaurantEntity> getAllRestaurants();
}
