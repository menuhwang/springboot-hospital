package com.likelion.hospital.utils;

import com.likelion.hospital.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {
    private final String secret;
    private final long tokenExpiration;

    public JwtTokenUtil(@Value("${util.jwt.secret}") String secret, @Value("${util.jwt.tokenExpiration}") long tokenExpiration) {
        System.out.println(tokenExpiration);
        this.secret = secret;
        this.tokenExpiration = tokenExpiration * 1000;
    }

    public String generateToken(User user) {
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("username", user.getUserName());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
