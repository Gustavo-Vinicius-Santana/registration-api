package br.com.projeto_registration.registration_api.service;

import br.com.projeto_registration.registration_api.dto.UserAuthLoginRequestDto;
import br.com.projeto_registration.registration_api.dto.UserAuthLoginResponseDto;
import br.com.projeto_registration.registration_api.dto.UserAuthRequestDto;
import br.com.projeto_registration.registration_api.dto.UserAuthResponseDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserAuthService {
    UserAuthResponseDto registerUser(UserAuthRequestDto dto);
    UserAuthLoginResponseDto loginUser(UserAuthLoginRequestDto dto);
    UserDetails loadUserByUsername (String username);
}
