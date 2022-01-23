package com.egorkuban.restaurantvote.repository;

import com.egorkuban.restaurantvote.jpa.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu,Long> {

    Optional<Menu> findByRestaurantIdAndIsActual(Long restaurantId, Boolean isActual);
}
