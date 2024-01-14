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
@Table(name="tbl_Ventas_Internas")
public class VentaInterna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_propietario")
    private Propietario propietario;
    @OneToMany(mappedBy = "ventaInterna", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DetalleVentaInterna> detalle_ventas_internas=new ArrayList<>();
}
