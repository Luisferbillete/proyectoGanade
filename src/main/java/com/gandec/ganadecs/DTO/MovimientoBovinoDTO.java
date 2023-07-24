package com.gandec.ganadecs.DTO;

import com.gandec.ganadecs.Entity.Bovino;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor

@ToString
@EqualsAndHashCode
public class MovimientoBovinoDTO implements EntityDTO{



    public MovimientoBovinoDTO(String numero, LocalDate fecha_de_ingreso, LocalDate fecha_de_salida) {
        this.numero = numero;
        this.fecha_de_ingreso = fecha_de_ingreso;
        this.fecha_de_salida = fecha_de_salida;
    }

    public MovimientoBovinoDTO(String numero, LocalDate fecha_de_ingreso, LocalDate fecha_de_salida, long potreroId) {
        this.numero = numero;
        this.fecha_de_ingreso = fecha_de_ingreso;
        this.fecha_de_salida = fecha_de_salida;
        this.potreroId = potreroId;
    }

    private String numero;
    private LocalDate fecha_de_ingreso;
    private LocalDate fecha_de_salida;
    private long potreroId;



}
