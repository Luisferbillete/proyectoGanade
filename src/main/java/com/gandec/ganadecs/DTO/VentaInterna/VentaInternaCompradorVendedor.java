package com.gandec.ganadecs.DTO.VentaInterna;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class VentaInternaCompradorVendedor {
    private long id;
    private LocalDate fecha;
    private String comprador;
    private String vendedor;
    private double totalVenta;

    public VentaInternaCompradorVendedor(long id, LocalDate fecha, String comprador,
                                         String vendedor, double totalVenta) {
        this.id = id;
        this.fecha = fecha;
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.totalVenta = totalVenta;
    }
}
