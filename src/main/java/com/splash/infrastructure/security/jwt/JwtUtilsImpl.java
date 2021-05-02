package com.splash.infrastructure.security.jwt;

import com.splash.common.configuration.JwtConfiguration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtilsImpl implements JwtUtils {

    @Autowired
    JwtConfiguration jwtConfiguration;

    @Override
    public String generateToken(UserDetails userDetails) {
        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfiguration.getExpiry()))
                .signWith(SignatureAlgorithm.HS256, jwtConfiguration.getSecret()).compact();
    }

    @Override
    public String extractUsername(String token){
        return Jwts
                .parser()
                .setSigningKey(jwtConfiguration.getSecret())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
