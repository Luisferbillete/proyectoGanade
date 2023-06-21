package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="partos")
@Data
public class Parto {
    @Id
    private String numero;
    private Date fecha_de_parto;
    private Date fecha_de_destete;
    private String nombre;
    @ManyToOne(fetch = FetchType.LAZY ,cascade= CascadeType.ALL)
    @JoinColumn(name="bovino_numero",nullable = false)
    private Bovino bovino;
    @OneToMany(mappedBy = "parto")
    private List<Cria> crias;

}
