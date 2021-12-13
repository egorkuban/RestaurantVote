package com.egorkuban.restaurantvote.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "PUBLIC", name = "RESTAURANT")
@Getter
@Setter
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "restaurantId")
    private List<MealEntity> meals;

    @OneToMany(mappedBy = "restaurants")
    private List<VoteEntity> votes;

}
