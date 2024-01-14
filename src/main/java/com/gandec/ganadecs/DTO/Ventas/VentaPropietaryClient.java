package com.gandec.ganadecs.DTO.Ventas;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class VentaPropietaryClient {
    private long id;
    private LocalDate fecha;
    private String lugar;
    private String cliente;
    private double totalVenta;

    public VentaPropietaryClient(long id, LocalDate fecha, String lugar, String cliente, double totalVenta) {
        this.id = id;
        this.fecha = fecha;
        this.lugar = lugar;
        this.cliente = cliente;
        this.totalVenta = totalVenta;

    }
}
