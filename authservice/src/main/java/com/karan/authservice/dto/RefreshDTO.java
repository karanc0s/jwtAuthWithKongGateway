package com.karan.authservice.dto;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RefreshDTO {

    private String refreshToken;

}
