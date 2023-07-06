package com.gandec.ganadecs.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BovinoDTO implements EntityDTO {
    @NotEmpty
    private String numero;
    private Date Fecha_de_nacimiento;
    private Date Fecha_de_ingreso;
    private String sexo;
    private String color,raza;

    private int Propietario_id;
}
