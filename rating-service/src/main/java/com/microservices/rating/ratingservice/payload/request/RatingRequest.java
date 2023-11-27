package com.microservices.rating.ratingservice.payload.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingRequest {
    private int hotelId;
    private int userId;
    private int ratings;
    private String remarks;
}
