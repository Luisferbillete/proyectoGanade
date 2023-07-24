package com.gandec.ganadecs.DTO;

import lombok.Data;

import java.time.LocalDate;
@Data
public class MovimientosDTO {
    private String numero;
    private LocalDate fecha;
}
