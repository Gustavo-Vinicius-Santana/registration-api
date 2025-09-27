package br.com.projeto_registration.registration_api.controller;

import br.com.projeto_registration.registration_api.dto.CpfRequestDto;
import br.com.projeto_registration.registration_api.dto.UserDto;
import br.com.projeto_registration.registration_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor

public class UserControllerV1 {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDto>> findAll(
            @PageableDefault(size = 5, sort = {"name"}, direction = Sort.Direction.ASC ) Pageable pagination){
        return ResponseEntity.ok(userService.findAll(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/by-email")
    public ResponseEntity<UserDto> findByEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> save(@Valid @RequestBody UserDto userDto, UriComponentsBuilder uriBuilder) {
        var userDtoSaved = userService.save(userDto);

        URI uri = uriBuilder.path("/api/v1/pessoas/{id}").buildAndExpand(userDtoSaved.id()).toUri();

        return ResponseEntity.created(uri).body(userDtoSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable("id") UUID id, @Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.update(id, userDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateCpf(@PathVariable("id") UUID id, @Valid @RequestBody CpfRequestDto requestData){
        return ResponseEntity.ok(userService.updateCpf(id, requestData.getCpf()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") UUID id){
        userService.deleteId(id);

        return ResponseEntity.noContent().build();
    }
}
