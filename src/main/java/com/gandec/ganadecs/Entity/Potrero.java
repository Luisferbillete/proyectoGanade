package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="potreros")
@Data
public class Potrero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

   private Long id;
   private String nombre;
   private long Hectareas;
    @OneToMany(mappedBy = "potrero")
   private List<MovimientoBovino> movimientoBovinos;

}
