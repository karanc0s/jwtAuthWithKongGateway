package com.karan.authservice.controller;

import com.karan.authservice.dto.AuthRequestDTO;
import com.karan.authservice.dto.AuthResponseDTO;
import com.karan.authservice.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login (
            @RequestBody AuthRequestDTO authRequestDTO
    ) {
        AuthResponseDTO responseDTO = authService.login(authRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register (
            @RequestBody AuthRequestDTO authRequestDTO
    ) {
        AuthResponseDTO responseDTO = authService.register(authRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validate(
            @RequestHeader(value = "Authorization" , required = true) String token
    ){
        String userId = authService.validateToken(token);
        return ResponseEntity.ok(userId);
    }
}
