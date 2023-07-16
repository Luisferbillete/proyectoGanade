package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="potreros")
@Data
public class Potrero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

   private Long id;
    @Column(name ="nombre")
   private String nombre;
    @Column(name ="Hectareas")
    private long hectareas;
    @OneToMany(mappedBy = "potrero")
   private List<MovimientoBovino> movimientoBovinos;

}
