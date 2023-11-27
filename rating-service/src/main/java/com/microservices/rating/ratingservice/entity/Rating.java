package com.microservices.rating.ratingservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.lang.annotation.Documented;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ratingId;

    @Column(name = "hotel_id")
    private int hotelId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "ratings")
    private int ratings;

    @Column(name = "remarks")
    private String remarks;
}
