package com.egorkuban.restaurantvote.repository;

import com.egorkuban.restaurantvote.jpa.model.Meal;
import com.egorkuban.restaurantvote.jpa.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {

    List<Meal> findAllByDateAndRestaurant(LocalDate date, Restaurant restaurant);
}
