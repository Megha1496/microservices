package com.microservices.hotel.hotelservice.controller;

import com.microservices.hotel.hotelservice.entity.Hotel;
import com.microservices.hotel.hotelservice.payloads.request.HotelRequest;
import com.microservices.hotel.hotelservice.payloads.response.BaseResponse;
import com.microservices.hotel.hotelservice.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/hotels")
    public ResponseEntity<?> createHotel(@Valid @RequestBody HotelRequest hotelRequest){
        Hotel hotel=hotelService.createHotel(hotelRequest);
        return ResponseEntity.ok(new BaseResponse<>("hotel created successfully",true,hotel));
    }

    @GetMapping("/hotels")
    public ResponseEntity<?>fetchHotels(){
        List<Hotel> hotels= hotelService.fetchHotels();
        return ResponseEntity.ok(new BaseResponse<>("list of hotels",true,hotels));

    }

    @GetMapping("/{id}")
    public ResponseEntity<?>fetchHotelById(@PathVariable int id){
        Hotel hotel=hotelService.fetchHotelById(id);
        return ResponseEntity.ok(new BaseResponse<>("Fetch Hotel Successfully",true,hotel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>updateHotel(@PathVariable int hotelId,@Valid @RequestBody HotelRequest hotelRequest){
        Hotel hotel=hotelService.updateHotel(hotelId,hotelRequest);
        return ResponseEntity.ok(new BaseResponse<>("Hotel Update Successfully",true,hotel));
    }
}
