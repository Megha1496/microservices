package com.microservices.rating.ratingservice.payload.response;

import com.microservices.rating.ratingservice.entity.Rating;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BaseResponse<T> {
    private String message;
    private boolean status;
    private T body;

    public BaseResponse(String message, boolean status, T body) {
        this.message=message;
        this.status=status;
        this.body=body;
    }

    public BaseResponse(T ratings) {
        this.body=ratings;
    }
}
