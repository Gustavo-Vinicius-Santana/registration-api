package br.com.projeto_registration.registration_api.service;

import br.com.projeto_registration.registration_api.dto.*;
import br.com.projeto_registration.registration_api.models.User;
import br.com.projeto_registration.registration_api.models.UserAuth;
import br.com.projeto_registration.registration_api.repository.UserAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthServiceIml implements UserDetailsService, UserAuthService {

    private final UserService userService;
    private final UserAuthRepository userAuthRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAuthRepository.findByEmailIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }

    @Override
    public UserAuthResponseDto registerUser(UserAuthRequestDto dto) {

        UserDto userDto = new UserDto(
                null,
                dto.name(),
                dto.email(),
                dto.phone(),
                dto.cpf()
        );

        UserDto savedUserDto = userService.save(userDto);

        UserAuth userAuth = new UserAuth();
        userAuth.setEmail(dto.email());
        userAuth.setPassword(passwordEncoder.encode(dto.password()));
        userAuth.setUser(User.fromDto(savedUserDto)); // associa o user persistido

        UserAuth savedUserAuth = userAuthRepository.save(userAuth);

        return new UserAuthResponseDto(savedUserAuth);
    }

    @Override
    public UserAuthLoginResponseDto loginUser(UserAuthLoginRequestDto dto) {
        UserAuth userAuth = userAuthRepository.findByEmailIgnoreCase(dto.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + dto.email()));

        if (!passwordEncoder.matches(dto.password(), userAuth.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(userAuth);

        return new UserAuthLoginResponseDto(new UserAuthResponseDto(userAuth), token);
    }
}
