package com.gandec.ganadecs.DTO.Bovinos;

import com.gandec.ganadecs.DTO.EntityDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BovDTO implements EntityDTO {
    private String numero;
    private String propietario;
    private LocalDate Fecha_de_nacimiento;
    private String sexo;
    private String raza;
    private String negocio;
    private long abaluo;
    private int kilos;
    private long preciokilo;
    private String categoria;
    private String estado;


    public BovDTO(String numero, LocalDate fecha_de_nacimiento,String sexo, String categoria, String estado) {
        this.numero = numero;
        Fecha_de_nacimiento = fecha_de_nacimiento;
        this.sexo = sexo;
        this.categoria = categoria;
        this.estado = estado;
    }

    public BovDTO(String numero, String propietario, LocalDate fecha_de_nacimiento, String sexo, String raza,
                  String negocio, long abaluo, int kilos, long preciokilo, String categoria, String estado) {
        this.numero = numero;
        this.propietario = propietario;
        Fecha_de_nacimiento = fecha_de_nacimiento;
        this.sexo = sexo;
        this.raza = raza;
        this.negocio = negocio;
        this.abaluo = abaluo;
        this.kilos = kilos;
        this.preciokilo = preciokilo;
        this.categoria = categoria;
        this.estado = estado;
    }
}
