package com.karan.authservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629";
    private static final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 2; // 2 minutes
    private static final long REFRESH_TOKEN_EXPIRATION = 1000L * 60 * 60 * 24 * 7; // 7 days


    public String extractUserName(String token) {
        return getClaims(token , Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return getClaims(token , Claims::getExpiration);
    }

    public String extractTokenType(String token){
        return getClaims( token , claims -> claims.get("TOKEN_TYPE")).toString();
    }

    public boolean validateToken(String token , String principalUsername) {
        final String username = extractUserName(token);
        return username.equals(principalUsername) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateAccessToken(String username) {
        return generateToken(username , ACCESS_TOKEN_EXPIRATION, "ACCESS_TOKEN");
    }
    public String generateRefreshToken(String username) {
        return generateToken(username , REFRESH_TOKEN_EXPIRATION , "REFRESH_TOKEN");
    }

    public <T> T getClaims(String token , Function<Claims, T> function){
        final Claims claims = getAllClaimsFromToken(token);
        return function.apply(claims);
    }

    private String generateToken(String username, long accessTokenExpiration , String type) {
        Map<String, Object> additionalClaims = new HashMap<>();
        additionalClaims.put("ROLE", "NORMAL_USER");
        additionalClaims.put("TOKEN_TYPE", type);

        return Jwts.builder()
                .setClaims(additionalClaims)
                .setSubject(username)
                .setIssuer("Authentication_Service")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .signWith(getSingKey() , SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSingKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSingKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}