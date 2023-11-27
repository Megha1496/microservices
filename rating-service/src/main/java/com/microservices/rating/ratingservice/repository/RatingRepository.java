package com.microservices.rating.ratingservice.repository;

import com.microservices.rating.ratingservice.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {

    List<Rating> findRatingByUserId(int userId);

    List<Rating> findRatingByHotelId(int hotelId);
}
