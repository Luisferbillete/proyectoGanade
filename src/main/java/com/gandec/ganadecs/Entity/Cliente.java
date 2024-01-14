package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long Id;
    @NotBlank(message = "El campo nombres no puede estar vacio")
    private String nombres;
    @NotBlank(message = "El campo apellidos no puede estar vacio")
    private String apellidos;
    private String direccion;
    private long telefono;
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Venta> ventas = new HashSet<>();
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<VentaInterna> ventas_internas = new HashSet<>();


}
