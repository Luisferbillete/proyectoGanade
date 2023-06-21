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

   private Integer id;
   private String nombre;
   private Integer Hectareas;
    @OneToMany(mappedBy = "potrero")
   private List<MovimientoBovino> movimientoBovinos;

}
