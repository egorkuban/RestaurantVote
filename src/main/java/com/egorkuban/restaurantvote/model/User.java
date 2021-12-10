package com.egorkuban.restaurantvote.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class User {
    private String name;
    private LocalDateTime checkInDate;
    private Set<Role> role;
}
