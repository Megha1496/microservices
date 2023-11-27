package com.microservices.hotel.hotelservice.payloads.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelRequest {

    @NotEmpty(message = "name can not be empty or null")
    private String name;

    @NotEmpty(message = "location can not be empty or null")
    private String location;

    @NotEmpty(message = "about can not be empty or null")
    private String about;
}
