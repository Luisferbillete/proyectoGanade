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
public class MovimientoBovinoDTO implements EntityDTO{
    private String numero;
    private LocalDate fecha_de_ingreso;
    private LocalDate fecha_de_salida;
    private long potreroId;
    private  String namepaddock;

    public MovimientoBovinoDTO(String numero, LocalDate fecha_de_ingreso, String namepaddock) {
        this.numero = numero;
        this.fecha_de_ingreso = fecha_de_ingreso;
        this.namepaddock = namepaddock;
    }

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





}
