package com.gandec.ganadecs.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class PartosDTO implements EntityDTO {
    private LocalDate fecha_de_parto;
    private String nombre;
private String Numero;

    public PartosDTO(LocalDate fecha_de_parto, String nombre, String numero) {
        this.fecha_de_parto = fecha_de_parto;
        this.nombre = nombre;
        Numero = numero;
    }
}
