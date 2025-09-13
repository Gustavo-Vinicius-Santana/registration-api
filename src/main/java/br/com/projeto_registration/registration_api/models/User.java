package br.com.projeto_registration.registration_api.models;

import br.com.projeto_registration.registration_api.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // gera construtor com todos os atributos
@Entity
@Table(name="user_tb")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, unique = true, length = 14) // CPF no formato 000.000.000-00
    private String cpf;

    public static User fromDto(UserDto userDto) {
        return new User(
                userDto.id(),
                userDto.name(),
                userDto.email(),
                userDto.phone(),
                userDto.cpf()
        );
    }
}