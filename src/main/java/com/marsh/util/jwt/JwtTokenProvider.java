package com.marsh.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    private final SecretKey secretKey;

    public JwtTokenProvider(@Value("${marsh.jwt.secret-key}") String jwtSecret) {
        this.secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(String username, String role, long expireIn) {
        var now = new Date();
        var token = Jwts.builder()
                .claim("username", username)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expireIn))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    public Claims resolveToken(HttpServletRequest request) {
        var token = request.getHeader("Authorization");

        if (token == null) {
            return null;
        }

        if (token.startsWith("Bearer ")) {
            token = token.replace("Bearer ", "");
        } else {
            throw new DecodingException("Decoding Error: Bearer Not Found");
        }

        return getClaims(token);
    }

    public String getUsername(String token) {
        return (String) getClaims(token).get("username");
    }

    public UsernamePasswordAuthenticationToken getAuthentication(Claims claims) {
        return new UsernamePasswordAuthenticationToken(
                claims.get("username"),
                null,
                getAuthority(claims)
        );
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private List<SimpleGrantedAuthority> getAuthority(Claims claims) {
        return List.of(new SimpleGrantedAuthority(claims.get("role", String.class)));
    }
}