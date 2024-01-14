package com.gandec.ganadecs.DTO.Parto;

import com.gandec.ganadecs.DTO.EntityDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Birthsdto implements EntityDTO {
    public Birthsdto(String numero, String nombre) {

        this.numero = numero;
        this.nombre = nombre;
    }
    private String numero;
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;
}
