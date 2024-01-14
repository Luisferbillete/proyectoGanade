package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Table(name="propietarios")
@Data

public class Propietario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    @Column(name="Nombres")
    private  String nombres;
    @Column(name="Apellidos")
    private String apellidos;
    @Column(name="Direccion")
    private String direccion ;
    @Column(name="Telefono")
    private Long telefonos;
    @OneToMany(mappedBy = "propietario")
    private List<Bovino> bovino;
    @OneToMany(mappedBy = "propietario")
    private List<Hierro> hierro;
    @OneToMany(mappedBy = "propietario")
    private List<VentaInterna> ventaInterna;
}
