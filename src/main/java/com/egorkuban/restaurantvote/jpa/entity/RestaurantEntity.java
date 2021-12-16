package com.egorkuban.restaurantvote.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "PUBLIC", name = "RESTAURANT")
@Getter
@Setter
@Accessors(chain = true)
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "restaurant")
    private List<MealEntity> meals;

}
