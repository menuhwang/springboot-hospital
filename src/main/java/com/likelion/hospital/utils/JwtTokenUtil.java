package com.likelion.hospital.utils;

import com.likelion.hospital.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {
    private final String secret;
    private final long tokenExpiration;
    private final String USERNAME_KEY = "username";
    private final String ID_KEY = "id";

    public JwtTokenUtil(@Value("${util.jwt.secret}") String secret, @Value("${util.jwt.tokenExpiration}") long tokenExpiration) {
        System.out.println(tokenExpiration);
        this.secret = secret;
        this.tokenExpiration = tokenExpiration * 1000;
    }

    public String generateToken(User user) {
        Claims claims = Jwts.claims();
        claims.put(ID_KEY, user.getId());
        claims.put(USERNAME_KEY, user.getUsername());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.SignatureException | MalformedJwtException exception) {
            // 잘못된 jwt signature
        } catch (io.jsonwebtoken.ExpiredJwtException exception) {
            // jwt 만료
        } catch (io.jsonwebtoken.UnsupportedJwtException exception) {
            // 지원하지 않는 jwt
        } catch (IllegalArgumentException exception) {
            // 잘못된 jwt 토큰
        }
        return false;
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        String username = claims.get(USERNAME_KEY).toString();

        User user = User.builder()
                .userName(username)
                .password(token)
                .build();

        return new UsernamePasswordAuthenticationToken(user, token, user.getAuthorities());
    }
}
