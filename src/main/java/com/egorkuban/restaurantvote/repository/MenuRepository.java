package com.egorkuban.restaurantvote.repository;

import com.egorkuban.restaurantvote.jpa.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu,Long> {
}
