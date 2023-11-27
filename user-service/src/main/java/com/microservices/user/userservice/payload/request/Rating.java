package com.microservices.user.userservice.payload.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Rating {
    private int ratingId;
    private int hotelId;
    private int userId;
    private int ratings;
    private String remarks;
    private Hotel hotel;
}
