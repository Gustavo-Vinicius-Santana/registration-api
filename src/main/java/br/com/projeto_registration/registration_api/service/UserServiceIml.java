package br.com.projeto_registration.registration_api.service;

import br.com.projeto_registration.registration_api.dto.UserDto;
import br.com.projeto_registration.registration_api.models.User;
import br.com.projeto_registration.registration_api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceIml implements UserService {
    private final UserRepository userRepository;

    @Override
    public Page<UserDto> findAll(Pageable pagination) {
        return userRepository.findAll(pagination).map(UserDto::new);
    }

    @Override
    public UserDto findById(UUID id) {
        return userRepository
                .findById(id)
                .map(UserDto::new).orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada."));
    }

    @Override
    public UserDto findByEmail(String email) {
        return userRepository.findByEmailContaining(email)
                .map(UserDto::new)
                .orElseThrow(() -> new EntityNotFoundException("pessoa não encontrada") );
    }

    @Override
    public UserDto save(UserDto userDto) {
        var user = User.fromDto(userDto);
        return new UserDto(userRepository.save(user));
    }

    @Transactional
    @Override
    public UserDto update(UUID id, UserDto userDto) {
        var user = userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("pessoa não encontrada"));

        user.setName(userDto.name());
        user.setCpf(userDto.cpf());
        user.setEmail(userDto.email());
        user.setPhone(userDto.phone());

        return new UserDto(userRepository.save(user));
    }

    @Override
    public UserDto updateCpf(UUID id, String cpf) {
        var user = userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("pessoa não encontrada"));

        user.setCpf(cpf);

        return new UserDto(userRepository.save(user));
    }

    @Override
    public void deleteId(UUID id) {
        var user = userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("pessoa não encontrada"));

        userRepository.deleteById(id);
    }
}
