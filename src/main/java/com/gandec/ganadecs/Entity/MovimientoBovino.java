package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Table(name="moviento_de reses")
@Data
public class MovimientoBovino {
    @Id
    private Integer id;
    private Date fecha_de_ingreso;
    private Date fecha_de_salida;
    private String numero;
    @ManyToOne(fetch = FetchType.LAZY ,cascade= CascadeType.ALL)
    @JoinColumn(name = "Potrero_id",nullable=false)
    private Potrero potrero;
    @ManyToOne(fetch = FetchType.LAZY ,cascade= CascadeType.ALL)
    @JoinColumn(name="Bovino_numero",nullable = false)
    private Bovino bovino;

}
