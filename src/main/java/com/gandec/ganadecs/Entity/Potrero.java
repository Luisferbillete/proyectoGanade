package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Potrero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

   private Long id;
   private String nombre;
    private long hectareas;
    @OneToMany(mappedBy = "potrero")
   private List<MovimientoBovino> movimientoBovinos;

}
