package com.egorkuban.restaurantvote.repository;

import com.egorkuban.restaurantvote.jpa.model.Menu;
import com.egorkuban.restaurantvote.jpa.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu,Long> {

    @Query("SELECT m FROM Menu m WHERE m.restaurant = :restaurant AND m.date = :menuDate AND m.isActual = true ")
    Optional<Menu> findActualMenu(@Param("restaurant") Restaurant restaurant,
                                  @Param("menuDate") LocalDate menuDate);
}