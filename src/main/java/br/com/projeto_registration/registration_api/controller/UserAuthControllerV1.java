package br.com.projeto_registration.registration_api.controller;

import br.com.projeto_registration.registration_api.dto.*;
import br.com.projeto_registration.registration_api.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserAuthControllerV1 {

    private final UserAuthService userAuthService;

    @PostMapping("/register")
    public ResponseEntity<UserAuthResponseDto> registerUser(
            @Valid @RequestBody UserAuthRequestDto requestDto) {

        UserAuthResponseDto registeredUser = userAuthService.registerUser(requestDto);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserAuthLoginResponseDto> loginUser(
            @Valid @RequestBody UserAuthLoginRequestDto loginDto) {

        UserAuthLoginResponseDto loginResponse = userAuthService.loginUser(loginDto);
        return ResponseEntity.ok(loginResponse);
    }
}
