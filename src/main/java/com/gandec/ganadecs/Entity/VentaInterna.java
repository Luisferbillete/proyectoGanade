package com.gandec.ganadecs.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VentaInterna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Temporal(TemporalType.DATE)
    private LocalDate fecha;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Cliente cliente;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Propietario propietario;
    @OneToMany(mappedBy = "ventaInterna", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<DetalleVentaInterna> detalle_ventas_internas=new ArrayList<>();
}
