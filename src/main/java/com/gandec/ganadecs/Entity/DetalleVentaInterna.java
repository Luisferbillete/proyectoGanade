package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_detalle_ventas_internas")
public class DetalleVentaInterna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int peso;
    private float precio;
    @ManyToOne(fetch=FetchType.EAGER , cascade = CascadeType.REMOVE)
    private VentaInterna ventaInterna;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Bovino bovino;
}
