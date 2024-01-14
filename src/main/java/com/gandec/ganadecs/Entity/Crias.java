package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_crias")
public class Crias {
    @Id
    private String numero;
    private String sexo;
    @Temporal(TemporalType.DATE)
    private LocalDate fecha_nacimiento;
    @Temporal(TemporalType.DATE)
    private LocalDate fecha_destete;
    private String raza;
    private String color;
    private String peso;
    private String estado;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinTable(name="tbl_crias_partos",
            joinColumns = @JoinColumn(name="crias_numero",referencedColumnName = "numero"),
            inverseJoinColumns = @JoinColumn(name="partos_numero",referencedColumnName = "numero"))
    private Set<Partos> partos= new HashSet<>();
}

