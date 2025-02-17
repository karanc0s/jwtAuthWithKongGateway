package com.karan.authservice.controller;

import com.karan.authservice.dto.AuthResponseDTO;
import com.karan.authservice.dto.RefreshDTO;
import com.karan.authservice.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class RefreshController {

    private final RefreshTokenService refreshTokenService;

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDTO> refreshAccessToken(
            @RequestBody RefreshDTO refreshDTO
    ) {
        AuthResponseDTO responseDTO = refreshTokenService.refreshAccessToken(refreshDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
