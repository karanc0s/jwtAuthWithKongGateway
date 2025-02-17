package com.karan.authservice.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthResponseDTO {

    private String accessToken;

    private String refreshToken;

}
