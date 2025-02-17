package com.karan.authservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AccessTokenExpiredException extends RuntimeException {
    public AccessTokenExpiredException() {
        super("Access Token has expired");
    }
    public AccessTokenExpiredException(String message) {
        super(message);
    }
}
