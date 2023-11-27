package com.microservices.rating.ratingservice.service;

import com.microservices.rating.ratingservice.entity.Rating;
import com.microservices.rating.ratingservice.payload.request.RatingRequest;

import java.util.List;

public interface RatingService {
    Rating createRating(RatingRequest ratingRequest);

    List<Rating> fetchRatings();

    Rating fetchRatingById(int id);

    List<Rating> fetchRatingsByUser(int userId);

    List<Rating> fetchRatingsByHotel(int hotelId);
}
