package com.gandec.ganadecs.DTO.Crias;

import com.gandec.ganadecs.DTO.EntityDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class CriasPorParto implements EntityDTO

{
    private String numero;
    private String sexo;
    private LocalDate fecha_nacimiento;
    private LocalDate fecha_destete;
    private String raza;
    private String color;
    private String peso;
    private String estado;

    public CriasPorParto(String numero, String sexo, LocalDate fecha_nacimiento,
                         LocalDate fecha_destete, String raza, String color, String peso, String estado) {
        this.numero = numero;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.fecha_destete = fecha_destete;
        this.raza = raza;
        this.color = color;
        this.peso = peso;
        this.estado = estado;
    }


}
