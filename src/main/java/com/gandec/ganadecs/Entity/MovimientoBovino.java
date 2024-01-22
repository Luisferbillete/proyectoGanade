package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class MovimientoBovino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.DATE)
    private LocalDate fecha_de_ingreso;
    @Temporal(TemporalType.DATE)
    private LocalDate fecha_de_salida;

    @ManyToOne(fetch = FetchType.EAGER ,cascade= CascadeType.REMOVE)

    private Potrero potrero;
    @ManyToOne(fetch = FetchType.EAGER ,cascade= CascadeType.REMOVE)

    private Bovino bovino;




}
