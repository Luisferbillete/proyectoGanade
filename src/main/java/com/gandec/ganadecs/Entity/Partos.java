package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Partos {
    @Id

    private String numero;
    private String nombre;
    @ManyToMany(mappedBy = "partos")
    private Set<Crias> crias = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private Bovino bovino;
}
