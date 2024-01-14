package com.gandec.ganadecs.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
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
    @NotEmpty(message = "Selecione un tipo de negocio")
    private String negocio;
    private long abaluo;
    private int kilos;
    private long preciokilo;

    public BovinoDTO(String numero, LocalDate fecha_de_nacimiento,  String sexo, String color, String raza) {
        this.numero = numero;
        Fecha_de_nacimiento = fecha_de_nacimiento;
        this.sexo = sexo;
        this.color = color;
        this.raza = raza;
    }
}
