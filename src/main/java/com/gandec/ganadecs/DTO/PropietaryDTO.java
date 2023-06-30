package com.gandec.ganadecs.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropietaryDTO implements EntityDTO{
    private Integer  id;
    private String nombres;
    private String apellidos;
    private String direccion;
    private Integer telefono;
}
