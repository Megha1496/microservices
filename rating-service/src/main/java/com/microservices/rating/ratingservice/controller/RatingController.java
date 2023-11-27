package com.microservices.rating.ratingservice.controller;

import com.microservices.rating.ratingservice.entity.Rating;
import com.microservices.rating.ratingservice.payload.request.RatingRequest;
import com.microservices.rating.ratingservice.payload.response.BaseResponse;
import com.microservices.rating.ratingservice.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/ratings")
    public ResponseEntity<?> createRatings(@RequestBody RatingRequest ratingRequest){
        Rating rating=ratingService.createRating(ratingRequest);
        return ResponseEntity.ok(new BaseResponse<>("rating create sucessfully",true,rating));
    }

    @GetMapping("/ratings")
    public ResponseEntity<?> getRatings(){
        List<Rating> rating=ratingService.fetchRatings();
        return ResponseEntity.ok(new BaseResponse<>("List of all ratings",true,rating));

    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getRatingById(@PathVariable int id){
        Rating rating=ratingService.fetchRatingById(id);
        return ResponseEntity.ok(new BaseResponse<>("Fetch rating by id",true,rating));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getRatingsByUser(@PathVariable int userId){
        List<Rating> ratings=ratingService.fetchRatingsByUser(userId);
        return ResponseEntity.ok(new BaseResponse<>(ratings));

    }
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<?> getRatingsByHotel(@PathVariable int hotelId){
        List<Rating> ratings=ratingService.fetchRatingsByHotel(hotelId);
        return ResponseEntity.ok(new BaseResponse<>("Fetch rating by hotel id",true,ratings));

    }

}
