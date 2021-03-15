package com.splash.infrastructure.security.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtUtils {
    String generateToken(UserDetails userDetails);
    String extractUsername(String token);
}
