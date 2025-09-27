package br.com.projeto_registration.registration_api.controller;

import br.com.projeto_registration.registration_api.dto.EventDto;
import br.com.projeto_registration.registration_api.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor

public class EventControllerV1 {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<Page<EventDto>> findAll(
            @PageableDefault(size = 5, sort = {"name"}, direction = Sort.Direction.ASC)Pageable pagination){
        return ResponseEntity.ok(eventService.findAll(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> findById(@PathVariable("id") UUID id){
        return ResponseEntity.ok(eventService.findById(id));
    }

    @GetMapping("/find-by-cep/{cep}")
    public ResponseEntity<EventDto> findByCep(@PathVariable("cep") String cep){
        return ResponseEntity.ok(eventService.findByCep(cep));
    }

    @PostMapping
    public ResponseEntity<EventDto> save(@Valid @RequestBody EventDto eventDto){
        EventDto saved = eventService.save(eventDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable("id") UUID id, @Valid @RequestBody EventDto eventDto){
        return ResponseEntity.ok(eventService.update(id, eventDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") UUID id){
        eventService.deleteId(id);
        return ResponseEntity.noContent().build();
    }

}
