package com.gandec.ganadecs.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropietaryDTO implements EntityDTO{
    private long id;
    @NotEmpty(message = "El Nombre es requerido")
    private String nombres;
    @NotEmpty(message = "El Apellido es requerido")

    private String apellidos;

    private String direccion;
    private Long telefonos;
    private List<BovinoDTO> bovinoDTOS;
}
