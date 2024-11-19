package com.group9.eventgaze.utils;


import io.jsonwebtoken.*;

import org.springframework.stereotype.Component;

import java.util.Date;



@Component
public class JwtTokenUtil {


    private static final String key = "nahinpH6L13PejYffzqajC6Kco+G8bqneE+hTpriZtEY";

    private static final long EXPIRATION_TIME = 86400000; // 1 hour in milliseconds


    public String generateToken(Long userId, String role) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("role", role)
                .setIssuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()  // Correct API call for version 0.12.6
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token) {
        try {
            getClaimsFromToken(token);
            return true;
        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
