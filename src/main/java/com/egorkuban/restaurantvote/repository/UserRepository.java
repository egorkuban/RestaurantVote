package com.egorkuban.restaurantvote.repository;

import com.egorkuban.restaurantvote.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Email;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(@Email String email);
}