package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data

public class Propietario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    private  String nombres;
    private String apellidos;
    private String direccion ;
    private Long telefonos;
    @OneToMany(mappedBy = "propietario")
    private List<Bovino> bovino;
    @OneToMany(mappedBy = "propietario")
    private List<Hierro> hierro;
    @OneToMany(mappedBy = "propietario")
    private List<VentaInterna> ventaInterna;
}
