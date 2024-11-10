package com.gandec.ganadecs.DTO.Bovinos;

import com.gandec.ganadecs.DTO.EntityDTO;
import com.gandec.ganadecs.DTO.Propietary.PropietaryComboDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class BovinosGetAll implements EntityDTO {
    private String numero;
    private String propietario;
    private String sexo;
    private String color;
    private String raza;
    private String negocio;
    private long abaluo;
    private int kilos;
    private double precioKilo;
    private LocalDate Fecha_de_nacimiento;
    private String potrero;
    private String categoria;


    public BovinosGetAll(String numero, String propietario, String sexo, String color, String raza, String negocio, long abaluo, int kilos, double precioKilo, LocalDate Fecha_de_nacimiento, String potrero, String categoria) {
        this.numero = numero;
        this.propietario = propietario;
        this.sexo = sexo;
        this.color = color;
        this.raza = raza;
        this.negocio = negocio;
        this.abaluo = abaluo;
        this.kilos = kilos;
        this.precioKilo = precioKilo;
        this.Fecha_de_nacimiento = Fecha_de_nacimiento;
        this.potrero = potrero;
        this.categoria = categoria;
    }
}
