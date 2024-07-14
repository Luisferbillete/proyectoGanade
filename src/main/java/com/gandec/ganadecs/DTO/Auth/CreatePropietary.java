package com.gandec.ganadecs.DTO.Auth;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CreatePropietary {
    private String nombres;
    private String apellidos;
    private String direccion;
    private Long telefonos;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Column(unique = true)
    private String username;
    @NotBlank
    private String password;
    private Set<String> roles;
}
