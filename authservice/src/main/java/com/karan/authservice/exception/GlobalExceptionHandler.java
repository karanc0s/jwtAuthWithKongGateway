package com.karan.authservice.exception;

import com.karan.authservice.dto.ErrorResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadCredentialException(
            BadCredentialsException ex,
            WebRequest request
    ) {
        ErrorResponseDTO errorResponse =  ErrorResponseDTO.builder()
                .apiPath(request.getDescription(false))
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotException(
            ResourceNotFound ex,
            WebRequest request
    ) {
        ErrorResponseDTO errorResponse =  ErrorResponseDTO.builder()
                .apiPath(request.getDescription(false))
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponseDTO> handleJWTExpiredException(
            ExpiredJwtException ex,
            WebRequest request
    ){
        ErrorResponseDTO errorResponse =  ErrorResponseDTO.builder()
                .apiPath(request.getDescription(false))
                .status(HttpStatus.UNAUTHORIZED)
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessTokenExpiredException.class)
    public ResponseEntity<ErrorResponseDTO> handleTokenExpiredException(
            AccessTokenExpiredException ex,
            WebRequest request
    ){
        ErrorResponseDTO errorResponse =  ErrorResponseDTO.builder()
                .apiPath(request.getDescription(false))
                .status(HttpStatus.UNAUTHORIZED)
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleAlreadyExistsException(
            AlreadyExistsException ex,
            WebRequest request
    ){
        ErrorResponseDTO errorResponse =  ErrorResponseDTO.builder()
                .apiPath(request.getDescription(false))
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidTokenException(
            InvalidTokenException ex,
            WebRequest request
    ){
        ErrorResponseDTO errorResponse =  ErrorResponseDTO.builder()
                .apiPath(request.getDescription(false))
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
