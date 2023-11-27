package com.microservices.hotel.hotelservice.service.impl;

import com.microservices.hotel.hotelservice.entity.Hotel;
import com.microservices.hotel.hotelservice.exceptionHandling.ResourceNotFoundException;
import com.microservices.hotel.hotelservice.payloads.request.HotelRequest;
import com.microservices.hotel.hotelservice.repository.HotelRepository;
import com.microservices.hotel.hotelservice.service.HotelService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;

    public HotelServiceImpl(ModelMapper modelMapper, HotelRepository hotelRepository) {
        this.modelMapper = modelMapper;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel createHotel(HotelRequest hotelRequest) {
        Hotel hotel=this.modelMapper.map(hotelRequest,Hotel.class);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> fetchHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel fetchHotelById(int id) {
        Hotel hotel= hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("hotel","id",id));
        return hotel;
    }

    @Override
    public Hotel updateHotel(int hotelId, HotelRequest hotelRequest) {
        Hotel hotel= hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("hotel","id",hotelId));
        hotel.setName(hotelRequest.getName());
        hotel.setLocation(hotelRequest.getLocation());
        hotel.setAbout(hotelRequest.getAbout());
        return hotelRepository.save(hotel);
    }
}
