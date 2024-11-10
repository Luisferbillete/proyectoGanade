package com.gandec.ganadecs.DTO.Bovinos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gandec.ganadecs.DTO.EntityDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBovino implements EntityDTO {
    private String numero;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate fecha_de_nacimiento;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate Fecha_de_ingreso;
    private String sexo;
    private String color,raza;
    private long propietario;
    private long potrero;
    @NotEmpty(message = "Selecione el tipo de negocio")
    private String negocio;
    private long abaluo;
    private int kilos;
    private long preciokilo;
}
