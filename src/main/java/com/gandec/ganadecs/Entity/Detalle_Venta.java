package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Detalle_Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private int peso;
    private float precio;
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)

    private Venta venta;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)

    private Bovino bovino;
}
