package com.gandec.ganadecs.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

import java.util.List;

@Entity
@Table(name="partos")
@Data
public class Parto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    @JsonFormat(pattern = "dd/MM/yyyy",timezone = "GMT-5")
    @Temporal(TemporalType.DATE)
    private LocalDate fecha_de_parto;
    @Temporal(TemporalType.DATE)
    private LocalDate fecha_de_destete;
    private String nombre;
    @ManyToOne(fetch = FetchType.LAZY ,cascade= CascadeType.ALL)
    @JoinColumn(name="bovino_numero",nullable = false)
    private Bovino bovino;
    @OneToMany(mappedBy = "parto")
    private List<Cria> crias;


}
