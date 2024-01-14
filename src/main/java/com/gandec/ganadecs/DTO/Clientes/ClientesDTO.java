package com.gandec.ganadecs.DTO.Clientes;

import com.gandec.ganadecs.DTO.EntityDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientesDTO implements EntityDTO {
    private long id;
    @NotBlank(message = "El campo nombres no puede estar vacio")
    private String nombres;
    @NotBlank(message = "El campo apellidos no puede estar vacio")
    private String apellidos;
    private String direccion;
    private long telefono;
}
