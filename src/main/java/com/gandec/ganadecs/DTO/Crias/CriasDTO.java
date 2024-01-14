package com.gandec.ganadecs.DTO.Crias;

import com.gandec.ganadecs.DTO.EntityDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriasDTO implements EntityDTO {
    private String numero;
    @NotEmpty(message = "El campo sexo no puede estar vacio")
    private String sexo;
    @NotNull(message = "El campo fecha de nacimiento no puede estar vacio")
    private LocalDate fecha_nacimiento;
    private LocalDate fecha_destete;
    private String raza;
    private String color;
    private String peso;
    private String estado;
    private String bovino;
}
