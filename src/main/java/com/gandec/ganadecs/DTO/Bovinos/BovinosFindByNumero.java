package com.gandec.ganadecs.DTO.Bovinos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor

public class BovinosFindByNumero {
    private String numero;
    private long propietario;
    private String sexo;
    private String color;
    private String raza;
    private String negocio;
    private long abaluo;
    private int kilos;
    private long preciokilo;
    private LocalDate Fecha_de_nacimiento;
    private LocalDate Fecha_de_ingreso;


    public BovinosFindByNumero(String numero, long propietario, String sexo, String color, String raza, String negocio, long abaluo, int kilos, long preciokilo, LocalDate Fecha_de_nacimiento,LocalDate Fecha_de_ingreso) {
        this.numero = numero;
        this.propietario = propietario;
        this.sexo = sexo;
        this.color = color;
        this.raza = raza;
        this.negocio = negocio;
        this.abaluo = abaluo;
        this.kilos = kilos;
        this.preciokilo = preciokilo;
        this.Fecha_de_nacimiento = Fecha_de_nacimiento;
        this.Fecha_de_ingreso = Fecha_de_ingreso;
    }
}
