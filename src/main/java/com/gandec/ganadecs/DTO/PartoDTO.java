package com.gandec.ganadecs.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gandec.ganadecs.Entity.Bovino;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter

@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PartoDTO implements EntityDTO {
    @JsonFormat(pattern = "dd/MM/yyyy",timezone = "GMT-5")

    private LocalDate fecha_de_parto;
    private LocalDate fecha_de_destete;

    public PartoDTO(LocalDate fecha_de_parto, LocalDate fecha_de_destete, String nombre, Bovino bovino) {
        this.fecha_de_parto = fecha_de_parto;
        this.fecha_de_destete = fecha_de_destete;
        this.nombre = nombre;
        this.bovino = bovino;
    }

    private String nombre;
    private Bovino bovino;
}
