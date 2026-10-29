package com.wildev.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Service
public class JwtService {

    private final SecretKey signingKey;
    private final long expirationMs;

    public JwtService(
            @Value("${app.jwt.secret}") String base64Secret,
            @Value("${app.jwt.expiration-ms}") long expirationMs) {

        this.signingKey = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(base64Secret)
        );

        this.expirationMs = expirationMs;
    }

    public String generateToken(UserDetails userDetails) {
        Instant now = Instant.now();

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(expirationMs)))
                .signWith(signingKey)
                .compact();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenValid(
            String token,
            UserDetails userDetails) {

        Claims claims = extractAllClaims(token);

        String tokenSubject = claims.getSubject();
        String userDetailsUsername = userDetails.getUsername();

        System.out.println("[JWT Service] Token subject: '" + tokenSubject + "'");
        System.out.println("[JWT Service] UserDetails username: '" + userDetailsUsername + "'");
        System.out.println("[JWT Service] Son iguales: " + Objects.equals(tokenSubject, userDetailsUsername));
        System.out.println("[JWT Service] Expiracion: " + claims.getExpiration());
        System.out.println("[JWT Service] Es valida: " + (claims.getExpiration() != null && claims.getExpiration().after(new Date())));

        boolean isValid = Objects.equals(
                tokenSubject,
                userDetailsUsername
        )
                && claims.getExpiration() != null
                && claims.getExpiration().after(new Date());

        System.out.println("[JWT Service] Resultado final: " + isValid);

        return isValid;
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}