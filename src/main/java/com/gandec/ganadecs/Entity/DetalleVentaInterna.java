package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class DetalleVentaInterna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int peso;
    private float precio;
    private double totalventa;
    private String tipodeventa;
    @ManyToOne(fetch=FetchType.EAGER , cascade = CascadeType.MERGE)
    private VentaInterna ventaInterna;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Bovino bovino;
}
