package com.gandec.ganadecs.DTO.Parto;

import com.gandec.ganadecs.DTO.EntityDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class PartosFindPropietarioDTO implements EntityDTO {
    private String numero;
    private String nombre;
    private LocalDate fechaDeParto;
    private String numeroCria;
    private String sexo;
    private String raza;
    private String estado;

    public PartosFindPropietarioDTO(String numero, String nombre, LocalDate fechaDeParto, String numeroCria,
                                    String sexo, String raza, String estado) {
        this.numero = numero;
        this.nombre = nombre;
        this.fechaDeParto = fechaDeParto;
        this.numeroCria = numeroCria;
        this.sexo = sexo;
        this.raza = raza;
        this.estado = estado;
    }
}
