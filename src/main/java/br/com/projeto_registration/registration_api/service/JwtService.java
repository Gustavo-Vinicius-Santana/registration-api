package br.com.projeto_registration.registration_api.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(UserDetails userDetails);
    String generateToken(UserDetails userDetails, long expirationMillis);
    String extractUsername(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
}
