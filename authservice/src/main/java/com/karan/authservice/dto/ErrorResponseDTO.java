package com.karan.authservice.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponseDTO {

    private HttpStatus status;

    private String apiPath;

    private String message;

    private LocalDateTime timestamp;

}
