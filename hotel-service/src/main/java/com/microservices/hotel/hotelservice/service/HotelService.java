package com.microservices.hotel.hotelservice.service;

import com.microservices.hotel.hotelservice.entity.Hotel;
import com.microservices.hotel.hotelservice.payloads.request.HotelRequest;

import java.util.List;

public interface HotelService {
    Hotel createHotel(HotelRequest hotelRequest);

    List<Hotel> fetchHotels();

    Hotel fetchHotelById(int id);

    Hotel updateHotel(int hotelId, HotelRequest hotelRequest);
}
