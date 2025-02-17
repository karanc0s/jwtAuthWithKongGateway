package com.karan.authservice.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthRequestDTO {

    private String username;

    private String password;

}
