package com.gandec.ganadecs.DTO.Crias;

import com.gandec.ganadecs.DTO.EntityDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class Cdto implements EntityDTO
{
    String numero;
    LocalDate fecha_destete;
    LocalDate fecha_nacimiento;

    public Cdto(String numero, LocalDate fecha_destete,LocalDate fecha_nacimiento) {
        this.numero = numero;
        this.fecha_destete = fecha_destete;
        this.fecha_nacimiento = fecha_nacimiento;
    }
}
