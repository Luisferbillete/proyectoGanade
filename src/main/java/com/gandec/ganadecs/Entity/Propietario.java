package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "username", name = "UK_username"),
        @UniqueConstraint(columnNames = "email", name = "UK_email")
})
public class Propietario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    @NotBlank
    private  String nombres;
    @NotBlank
    private String apellidos;
    @NotBlank
    private String direccion ;
    @Email
    @NotBlank
    @Size(max = 80)
    private String email;
    @NotBlank
    @Size(max = 30)
    private String username;
    @NotBlank
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "propietario_roles", joinColumns = @JoinColumn(name = "propietario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))

    private Set<RoleEntity> roles;

    private Long telefonos;
    @OneToMany(mappedBy = "propietario")
    private List<Bovino> bovino;
    @OneToMany(mappedBy = "propietario")
    private List<Hierro> hierro;
    @OneToMany(mappedBy = "propietario")
    private List<VentaInterna> ventaInterna;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
