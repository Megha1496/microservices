package com.microservices.rating.ratingservice.service.impl;

import com.microservices.rating.ratingservice.entity.Rating;
import com.microservices.rating.ratingservice.exceptionhandling.ResourceNotFoundException;
import com.microservices.rating.ratingservice.payload.request.RatingRequest;
import com.microservices.rating.ratingservice.repository.RatingRepository;
import com.microservices.rating.ratingservice.service.RatingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final ModelMapper modelMapper;

    public RatingServiceImpl(RatingRepository ratingRepository, ModelMapper modelMapper) {
        this.ratingRepository = ratingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Rating createRating(RatingRequest ratingRequest) {
        Rating rating=Rating.builder().hotelId(ratingRequest.getHotelId())
                .userId(ratingRequest.getUserId()).ratings(ratingRequest.getRatings())
                .remarks(ratingRequest.getRemarks()).build();
        Rating savedRating= this.ratingRepository.save(rating);
        return savedRating;
    }

    @Override
    public List<Rating> fetchRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating fetchRatingById(int id) {
        Rating rating= ratingRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Rating","id",id));
        return rating;
    }

    @Override
    public List<Rating> fetchRatingsByUser(int userId) {
        List<Rating> rating=ratingRepository.findRatingByUserId(userId);
        return rating;
    }

    @Override
    public List<Rating> fetchRatingsByHotel(int hotelId) {
        List<Rating> rating=ratingRepository.findRatingByHotelId(hotelId);
        return rating;
    }
}
