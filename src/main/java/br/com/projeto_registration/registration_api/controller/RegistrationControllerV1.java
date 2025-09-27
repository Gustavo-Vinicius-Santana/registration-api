package br.com.projeto_registration.registration_api.controller;

import br.com.projeto_registration.registration_api.dto.RegistrationRequestDTO;
import br.com.projeto_registration.registration_api.dto.RegistrationResponseDTO;
import br.com.projeto_registration.registration_api.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/registration")
@RequiredArgsConstructor

public class RegistrationControllerV1 {

    private final RegistrationService registrationService;

    @GetMapping
    public ResponseEntity<Page<RegistrationResponseDTO>> findAll(Pageable pageable) {
        Page<RegistrationResponseDTO> registrations = registrationService.findAll(pageable);
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationResponseDTO> findById(@PathVariable UUID id) {
        RegistrationResponseDTO registration = registrationService.findById(id);
        return ResponseEntity.ok(registration);
    }

    @PostMapping
    public ResponseEntity<RegistrationResponseDTO> save(@Valid @RequestBody RegistrationRequestDTO registrationRequestDTO) {
        RegistrationResponseDTO saved = registrationService.save(registrationRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistrationResponseDTO> update(@PathVariable UUID id,
                                                          @Valid @RequestBody RegistrationRequestDTO registrationRequestDTO) {
        RegistrationResponseDTO updated = registrationService.update(id, registrationRequestDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        registrationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
