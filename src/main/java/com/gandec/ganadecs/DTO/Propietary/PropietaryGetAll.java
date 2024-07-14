package com.gandec.ganadecs.DTO.Propietary;

import com.gandec.ganadecs.DTO.EntityDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@Getter
public class PropietaryGetAll implements EntityDTO{
    private long id;
    private String nombres;
    private String apellidos;
    private String direccion;
    private Long telefonos;
    private String email;

    public PropietaryGetAll(long id, String nombres, String apellidos, String direccion, Long telefonos, String email) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefonos = telefonos;
        this.email = email;
    }
}
