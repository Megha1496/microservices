package com.microservices.user.userservice.external.service;

import com.microservices.user.userservice.payload.request.Rating;
import com.microservices.user.userservice.payload.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    //create
    @PostMapping("/ratings")
    ResponseEntity<BaseResponse<Rating>> createRatings(Rating rating);

    //update
    @PutMapping("/rating/{ratingId}")
    ResponseEntity<BaseResponse<Rating>> updateRating(@PathVariable int ratingId, Rating rating);
}
