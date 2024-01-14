package com.gandec.ganadecs.DTO.Bovinos;

import com.gandec.ganadecs.DTO.EntityDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BovinosDTO implements EntityDTO {

    private String numero;
    private String propietario;
    private String sexo;
    private String color;
    private String raza;
    private String negocio;
    private long abaluo;
    private int kilos;
    private long preciokilo;
    private LocalDate fecha_de_nacimiento;
    private String categoria;

    public BovinosDTO(String numero,String propietario,String sexo,String color,String raza,
                      String negocio,long abaluo,int kilos,long preciokilo,LocalDate fecha_de_nacimiento, String categoria)
                       {
        this.numero = numero;
        this.propietario = propietario;
        this.sexo = sexo;
        this.color = color;
        this.raza = raza;
        this.negocio = negocio;
        this.abaluo = abaluo;
        this.kilos = kilos;
        this.preciokilo = preciokilo;
        this.fecha_de_nacimiento=fecha_de_nacimiento;
        this.categoria = categoria;
    }
}
