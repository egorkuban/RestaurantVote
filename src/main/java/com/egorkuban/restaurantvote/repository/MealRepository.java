package com.egorkuban.restaurantvote.repository;

import com.egorkuban.restaurantvote.jpa.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Dish, Long> {

}
