package com.egorkuban.restaurantvote.repository;

import com.egorkuban.restaurantvote.jpa.model.Dish;
import com.egorkuban.restaurantvote.jpa.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends JpaRepository<Dish, Long> {

    List<Dish> findAllByDateAndMenu_Restaurant(LocalDate date, Restaurant restaurant);
}
