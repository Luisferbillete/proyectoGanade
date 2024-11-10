package com.gandec.ganadecs.DTO.Detalles_ventas;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetallesVentasDTO {
    public DetallesVentasDTO(int peso, double precio, double totalventa, String bovino, String tipodeventa, String categoria) {
        this.peso = peso;
        this.precio = precio;
        this.totalventa = totalventa;
        this.bovino = bovino;
        this.tipodeventa = tipodeventa;
        this.categoria = categoria;
    }

    private int peso;
    private double precio;
    private double totalventa;
    private String bovino;
    private String tipodeventa;
    private String categoria;


}
