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
    private double totalventa;
    private String propietario;
    private String tipodeventa;
    private String sexo;
    private LocalDate fechanacimiento;
    private String categoria;

    public VentaClienteDetalleBovino(LocalDate fecha, String lugar, String cliente,
                                     String bovino, int peso,
                                     double precio, double totalventa, String categoria) {
        this.fecha = fecha;
        this.lugar = lugar;
        this.cliente = cliente;
        this.bovino = bovino;
        this.peso = peso;
        this.precio = precio;
        this.totalventa = totalventa;
        this.categoria = categoria;
    }

    public VentaClienteDetalleBovino(LocalDate fecha, String lugar, String cliente, String bovino, int peso, double precio, double totalventa, String propietario,String tipodeventa,String sexo,LocalDate fechanacimiento, String categoria) {
        this.fecha = fecha;
        this.lugar = lugar;
        this.cliente = cliente;
        this.bovino = bovino;
        this.peso = peso;
        this.precio = precio;
        this.totalventa = totalventa;
        this.propietario = propietario;
        this.tipodeventa = tipodeventa;
        this.sexo = sexo;
        this.fechanacimiento = fechanacimiento;
        this.categoria = categoria;
    }
}
