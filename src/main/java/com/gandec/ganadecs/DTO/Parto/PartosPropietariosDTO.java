package com.gandec.ganadecs.DTO.Parto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gandec.ganadecs.DTO.EntityDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PartosPropietariosDTO implements EntityDTO {
    private String Fullname;
    private String Numero;
    private String nombre;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate FechaDeParto;
    private String sexo;

    public PartosPropietariosDTO(String fullname, String numero, String nombre, LocalDate fechaDeParto, String sexo) {
        Fullname = fullname;
        Numero = numero;
        this.nombre = nombre;
        FechaDeParto = fechaDeParto;
        this.sexo = sexo;
    }
}
