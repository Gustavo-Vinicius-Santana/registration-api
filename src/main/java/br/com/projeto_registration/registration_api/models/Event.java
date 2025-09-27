package br.com.projeto_registration.registration_api.models;

import br.com.projeto_registration.registration_api.dto.EventDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // gera construtor com todos os atributos
@Entity
@Table(name="event_tb")
public class Event {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private LocalDateTime dateEvent;
    private String cep;
    private String city;
    private String state;

    public static Event fromDto(EventDto eventRequestDto){
        var event = new Event();

        event.setId(eventRequestDto.id());
        event.setName(eventRequestDto.name());
        event.setDateEvent(eventRequestDto.dateEvent());
        event.setCep(eventRequestDto.cep());

        return event;
    }
}
