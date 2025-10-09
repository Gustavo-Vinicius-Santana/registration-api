package br.com.projeto_registration.registration_api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_users")
public class UserAuth implements UserDetails {

    @Getter
    @Id
    @GeneratedValue
    private UUID id;

    private String email;
    private String password;

    @Getter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; // futuramente pode retornar roles/authorities
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}