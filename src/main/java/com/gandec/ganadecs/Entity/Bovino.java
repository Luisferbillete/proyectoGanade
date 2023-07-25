package com.gandec.ganadecs.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="bovinos")
@Data
public class Bovino {
 @Id
 //@GeneratedValue(strategy = GenerationType. )

   private String Numero;
    @JsonFormat(pattern = "dd/MM/yyyy",timezone = "GMT-5")

  private   Date Fecha_de_nacimiento;
   private LocalDate Fecha_de_ingreso;
   private String sexo;
   private String color,raza;
   private String negocio;
    private long abaluo;
   private int kilos;
   private long preciokilo;
    @ManyToOne(fetch = FetchType.LAZY ,cascade= CascadeType.REMOVE)
    @JoinColumn(name = "Propietario_id")
    private Propietario propietario;
    @OneToMany(mappedBy = "bovino")
    private List<MovimientoBovino> movimientoBovinos;
    @OneToMany(mappedBy = "bovino")
    private List<Parto> partos;

}
