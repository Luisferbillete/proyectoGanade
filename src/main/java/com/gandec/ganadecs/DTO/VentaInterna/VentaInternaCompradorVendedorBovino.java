package com.gandec.ganadecs.DTO.VentaInterna;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class VentaInternaCompradorVendedorBovino {
    private LocalDate fecha;
    private String comprador;
    private String vendedor;
    private String bovino;
    private int peso;
    private double precio;
    private double valor;
    private String categoria;

    public VentaInternaCompradorVendedorBovino(LocalDate fecha, String comprador, String vendedor, String bovino,
                                               int peso, double precio, double valor, String categoria) {
        this.fecha = fecha;
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.bovino = bovino;
        this.peso = peso;
        this.precio = precio;
        this.valor = valor;
        this.categoria = categoria;
    }
}
