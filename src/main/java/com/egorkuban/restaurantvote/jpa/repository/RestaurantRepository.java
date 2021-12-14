package com.egorkuban.restaurantvote.jpa.repository;

import com.egorkuban.restaurantvote.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

}
