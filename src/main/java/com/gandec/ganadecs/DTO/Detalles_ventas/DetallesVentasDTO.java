package com.gandec.ganadecs.DTO.Detalles_ventas;

import com.gandec.ganadecs.Entity.Bovino;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetallesVentasDTO {
    private int peso;
    private float precio;
    private double valor;
    private String bovino;
    private String categoria;

    public DetallesVentasDTO(int peso, float precio, double valor, String bovino,String categoria) {
        this.peso = peso;
        this.precio = precio;
        this.valor = valor;
        this.bovino = bovino;
        this.categoria = categoria;
    }
}
