package com.egorkuban.restaurantvote.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(schema = "public", name = "vote")
@Getter
@Setter
@Accessors(chain = true)
public class VoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RestaurantEntity restaurant;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Column(name = "date_vote")
    private LocalDate voteDate;
}
