package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="moviento_de_reses")
@Getter
@Setter

@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class MovimientoBovino {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Temporal(TemporalType.DATE)
    private LocalDate fecha_de_ingreso;
    @Temporal(TemporalType.DATE)
    private LocalDate fecha_de_salida;

    @ManyToOne(fetch = FetchType.LAZY ,cascade= CascadeType.ALL)
    @JoinColumn(name = "Potrero_id",nullable=false)
    private Potrero potrero;
    @ManyToOne(fetch = FetchType.LAZY ,cascade= CascadeType.ALL)
    @JoinColumn(name="Bovino_numero",nullable = false)
    private Bovino bovino;

    public MovimientoBovino(LocalDate fecha_de_ingreso, LocalDate fecha_de_salida, Bovino bovino) {
        this.fecha_de_ingreso = fecha_de_ingreso;
        this.fecha_de_salida = fecha_de_salida;
        this.bovino = bovino;
    }

    public MovimientoBovino() {

    }
}
