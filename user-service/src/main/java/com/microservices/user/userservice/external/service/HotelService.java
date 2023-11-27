package com.microservices.user.userservice.external.service;

import com.microservices.user.userservice.payload.request.Hotel;
import com.microservices.user.userservice.payload.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotel/{hotelId}")
    ResponseEntity<BaseResponse<Hotel>> getHotel(@PathVariable int hotelId);
}
