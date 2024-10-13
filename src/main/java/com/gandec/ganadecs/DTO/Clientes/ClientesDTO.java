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

    public ClientesDTO(long id, String nombres, String apellidos, String direccion, long telefono) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
    }
}
