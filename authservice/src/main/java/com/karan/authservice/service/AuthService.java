package com.karan.authservice.service;

import com.karan.authservice.dto.AuthRequestDTO;
import com.karan.authservice.dto.AuthResponseDTO;
import com.karan.authservice.entities.RefreshToken;
import com.karan.authservice.entities.UserCreds;
import com.karan.authservice.exception.AccessTokenExpiredException;
import com.karan.authservice.exception.AlreadyExistsException;
import com.karan.authservice.exception.BadCredentialsException;
import com.karan.authservice.exception.ResourceNotFound;
import com.karan.authservice.repository.UserCredRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserCredRepository userCredRepository;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    public AuthResponseDTO login(AuthRequestDTO authRequestDTO) {
        String username = authRequestDTO.getUsername();
        String password = authRequestDTO.getPassword();

        UserCreds creds = userCredRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFound("user not found")
        );
        if (!password.equals(creds.getPassword())) {
            throw new BadCredentialsException("invalid password");
        }

        String accessToken = jwtService.generateAccessToken(creds.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(creds);

        return AuthResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }

    public AuthResponseDTO register(AuthRequestDTO authRequestDTO) {
        String username = authRequestDTO.getUsername();
        String password = authRequestDTO.getPassword();

        Optional<UserCreds> optionalUserCreds = userCredRepository.findByUsername(username);
        if(optionalUserCreds.isPresent()){
            throw new AlreadyExistsException("username already exists");
        }

        UserCreds creds = new UserCreds();
        creds.setUsername(username);
        creds.setPassword(password);
        creds.setUserId(UUID.randomUUID().toString());

        creds = userCredRepository.save(creds);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(creds);
        String accessToken = jwtService.generateAccessToken(creds.getUsername());

        return AuthResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }


    public String validateToken(String token) {
        if(jwtService.isTokenExpired(token)){
            throw new AccessTokenExpiredException("Token is expired");
        }
        String username = jwtService.extractUserName(token);
        Optional<UserCreds> optionalUserCreds = userCredRepository.findByUsername(username);
        if(optionalUserCreds.isEmpty()){
            throw new ResourceNotFound("Not Found.... ISSUE!!!!");
        }
        return optionalUserCreds.get().getUserId();
    }
}
