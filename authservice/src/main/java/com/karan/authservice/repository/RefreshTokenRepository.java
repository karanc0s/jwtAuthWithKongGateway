package com.karan.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karan.authservice.entities.RefreshToken;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface RefreshTokenRepository extends JpaRepository<RefreshToken , Integer>{

    Optional<RefreshToken> findByUserId(String userId);

    Optional<RefreshToken> findByRefreshToken(String refreshToken);

}
