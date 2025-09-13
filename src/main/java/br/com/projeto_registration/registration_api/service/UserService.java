package br.com.projeto_registration.registration_api.service;

import br.com.projeto_registration.registration_api.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {
    Page<UserDto> findAll(Pageable pagination);
    UserDto findById(UUID id);
    UserDto findByEmail( String email);
    UserDto save (UserDto userDto);
    UserDto update(UUID id, UserDto userDto);
    UserDto updateCpf (UUID id, String cpf);
    void deleteId(UUID id);
}
