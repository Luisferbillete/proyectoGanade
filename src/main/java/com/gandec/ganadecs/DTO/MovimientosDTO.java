package com.gandec.ganadecs.DTO;


import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter

public class MovimientosDTO {
    private String numero;
    private LocalDate fecha_de_ingreso;
    private  String namepaddock;
    public MovimientosDTO(String numero, LocalDate fecha_de_ingreso, String namepaddock) {
        this.numero = numero;
        this.fecha_de_ingreso = fecha_de_ingreso;
        this.namepaddock = namepaddock;
    }


}
