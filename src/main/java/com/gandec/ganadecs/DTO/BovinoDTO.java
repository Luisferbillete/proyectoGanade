package com.gandec.ganadecs.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor


public class BovinoDTO implements EntityDTO {


    private String numero;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate Fecha_de_nacimiento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate Fecha_de_ingreso;
    private String sexo;
    private String color,raza;
    private long propietario;
    @NotEmpty(message = "Selecione el tipo de negocio")
    private String negocio;
    private long abaluo;
    private int kilos;
    private long preciokilo;


}
