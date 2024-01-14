package com.gandec.ganadecs.DTO.Bovinos;

import com.gandec.ganadecs.DTO.EntityDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class BovinesDTO implements EntityDTO {
    //private String Nombre;
    private String Numero;
    private LocalDate Fecha_de_nacimiento;
    private String Sexo;

    public BovinesDTO(String numero, LocalDate fecha_de_nacimiento, String sexo) {
        Numero = numero;
        Fecha_de_nacimiento = fecha_de_nacimiento;
        Sexo = sexo;
    }
}
