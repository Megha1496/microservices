package com.microservices.user.userservice.service.impl;

import com.microservices.user.userservice.exceptionHandling.ResourceNotFoundException;
import com.microservices.user.userservice.external.service.HotelService;
import com.microservices.user.userservice.model.User;
import com.microservices.user.userservice.payload.request.Hotel;
import com.microservices.user.userservice.payload.request.Rating;
import com.microservices.user.userservice.payload.request.UserRequest;
import com.microservices.user.userservice.payload.response.BaseResponse;
import com.microservices.user.userservice.repository.UserRepo;
import com.microservices.user.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Override
    public User createUser(UserRequest userRequest) {
        User user=this.modelMapper.map(userRequest,User.class);
       User savedUser= this.userRepository.save(user);
        return savedUser;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users= userRepository.findAll();
        return users;
    }

    @Override
    public User findUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", id));

        //use rest template to get rating data
        ResponseEntity<BaseResponse<List<Rating>>> ratingOfUser = restTemplate.exchange(
                "http://RATING-SERVICE/user/"+id+"/ratings",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        List<Rating> userRatings = ratingOfUser.getBody().getBody();
        if (userRatings != null && !userRatings.isEmpty()) {
            for (Rating rating : userRatings) {
                try {
                  // use feign client to get hotel data
                    ResponseEntity<BaseResponse<Hotel>> hotel=hotelService.getHotel(rating.getHotelId());
                    rating.setHotel(hotel.getBody().getBody());

                } catch (RestClientException e) {
                    e.printStackTrace();
                }
            }
            user.setRatings(userRatings);
        }
        return user;
    }


    @Override
    public User updateUser(int id, UserRequest userRequest) {
      User user=  userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user","id",id));
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setAbout(userRequest.getAbout());
      return userRepository.save(user);
    }

    @Override
    public User deleteUser(int id) {
        User user=  userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user","id",id));
        userRepository.delete(user);
        return user;
    }
}
