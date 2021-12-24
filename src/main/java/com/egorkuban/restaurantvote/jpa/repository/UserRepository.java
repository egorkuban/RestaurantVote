package com.egorkuban.restaurantvote.jpa.repository;

import com.egorkuban.restaurantvote.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Email;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(@Email String email);
}