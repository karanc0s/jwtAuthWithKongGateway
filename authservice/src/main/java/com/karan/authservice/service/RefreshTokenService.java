package com.karan.authservice.service;

import com.karan.authservice.dto.AuthResponseDTO;
import com.karan.authservice.dto.RefreshDTO;
import com.karan.authservice.entities.RefreshToken;
import com.karan.authservice.entities.UserCreds;
import com.karan.authservice.exception.ResourceNotFound;
import com.karan.authservice.repository.RefreshTokenRepository;
import com.karan.authservice.repository.UserCredRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RefreshTokenService {

    private RefreshTokenRepository refreshTokenRepository;
    private UserCredRepository userCredRepository;
    private JwtService jwtService;

    public AuthResponseDTO refreshAccessToken(RefreshDTO refreshDTO){

        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByRefreshToken(refreshDTO.getRefreshToken());
        if(optionalRefreshToken.isEmpty()){
            throw new ResourceNotFound("Refresh token not found");
        }
        RefreshToken refreshToken = optionalRefreshToken.get();
        if(refreshToken.isRevoked()){
            throw new RuntimeException("Refresh token is revoked");
        }

        UserCreds creds = userCredRepository.findByUserId(refreshToken.getUserId()).orElseThrow(
                () -> new RuntimeException("User credentials not found")
        );
        String newRefresh = jwtService.generateRefreshToken(creds.getUsername());

        refreshToken.setRefreshToken(newRefresh);
        refreshToken.setRevoked(false);

        refreshToken = refreshTokenRepository.save(refreshToken);

        String accessToken = jwtService.generateAccessToken(creds.getUsername());

        return AuthResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }

    public RefreshToken createRefreshToken(UserCreds userCreds){
        String token = jwtService.generateRefreshToken(userCreds.getUsername());
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByUserId(userCreds.getUserId());
        RefreshToken refreshToken = new RefreshToken();

        if(optionalRefreshToken.isPresent()){
            refreshToken = optionalRefreshToken.get();
        }

        refreshToken.setRefreshToken(token);
        refreshToken.setRevoked(false);
        refreshToken.setUserId(userCreds.getUserId());

        return refreshTokenRepository.save(refreshToken);
    }



}
