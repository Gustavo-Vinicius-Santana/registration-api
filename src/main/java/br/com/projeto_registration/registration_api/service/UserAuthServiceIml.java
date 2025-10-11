package br.com.projeto_registration.registration_api.service;

import br.com.projeto_registration.registration_api.dto.*;
import br.com.projeto_registration.registration_api.models.User;
import br.com.projeto_registration.registration_api.models.UserAuth;
import br.com.projeto_registration.registration_api.repository.UserAuthRepository;
import br.com.projeto_registration.registration_api.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAuthRepository.findByEmailIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }

    @Override
    public UserAuthResponseDto registerUser(UserAuthRequestDto dto) {

        // Cria e salva o User normalmente
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPhone(dto.phone());
        user.setCpf(dto.cpf());

        User savedUser = userRepository.save(user); // <-- Entidade gerenciada pelo Hibernate

        // Cria o UserAuth associado ao mesmo User
        UserAuth userAuth = new UserAuth();
        userAuth.setEmail(dto.email());
        userAuth.setPassword(passwordEncoder.encode(dto.password()));
        userAuth.setUser(savedUser); // ✅ associa a entidade gerenciada, não um clone

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
