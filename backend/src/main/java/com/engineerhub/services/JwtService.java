package com.engineerhub.services;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}") //value come from application.properties file
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String email) {

        return Jwts.builder()

                .setSubject(email)

                .setIssuedAt(new Date())

                .setExpiration(new Date(System.currentTimeMillis() + expiration))

                .signWith(getKey())

                .compact();
    }
    public String extractUsername(String token) {
        if (token == null || token.isBlank()) {
            return null;
        }

        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        if (token == null || token.isBlank() || userDetails == null) {
            return false;
        }

        final String username = extractUsername(token);

        return username != null
                && username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        if (token == null || token.isBlank()) {
            return true;
        }

        try {
            return extractExpiration(token).before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return true;
        }
    }

    private Date extractExpiration(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }
    
    

}