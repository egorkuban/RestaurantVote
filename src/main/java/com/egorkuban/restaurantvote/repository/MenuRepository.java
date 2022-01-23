package com.egorkuban.restaurantvote.repository;

import com.egorkuban.restaurantvote.jpa.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu,Long> {

    @Query("select m from Menu m where m.restaurant.id = ?1 and m.isActual = true")
    Optional<Menu> findByRestaurantIdAndIsActual(Long restaurantId);
}
