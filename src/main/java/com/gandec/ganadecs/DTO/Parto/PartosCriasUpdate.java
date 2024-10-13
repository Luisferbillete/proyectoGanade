package com.gandec.ganadecs.DTO.Parto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gandec.ganadecs.DTO.EntityDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class PartosCriasUpdate implements EntityDTO {
    private String Numero;
    private String nombre;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate fecha_nacimiento;
    private String raza;
    private String color;
    private String peso;

    public PartosCriasUpdate(String numero, String nombre, LocalDate fecha_nacimiento, String raza, String color, String peso, String sexo, String cria) {
        Numero = numero;
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.raza = raza;
        this.color = color;
        this.peso = peso;
        this.sexo = sexo;
        this.cria = cria;
    }

    private String sexo;
    private String cria;


}
