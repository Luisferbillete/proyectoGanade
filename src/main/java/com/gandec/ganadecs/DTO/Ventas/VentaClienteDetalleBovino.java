package com.gandec.ganadecs.DTO.Ventas;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class VentaClienteDetalleBovino {
    private LocalDate fecha;
    private String lugar;
    private String cliente;
    private String bovino;
    private int peso;
    private double precio;
    private double valor;
    private String categoria;

    public VentaClienteDetalleBovino(LocalDate fecha, String lugar, String cliente,
                                     String bovino, int peso,
                                     double precio, double valor, String categoria) {
        this.fecha = fecha;
        this.lugar = lugar;
        this.cliente = cliente;
        this.bovino = bovino;
        this.peso = peso;
        this.precio = precio;
        this.valor = valor;
        this.categoria = categoria;
    }
}
