package com.gandec.ganadecs.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Bovino {
 @Id
   private String Numero;
 @JsonFormat(pattern = "yyyy/MM/dd")
  private   LocalDate fecha_de_nacimiento;
 @JsonFormat(pattern = "yyyy/MM/dd")
   private LocalDate Fecha_de_ingreso;
   private String sexo;
   private String color,raza;
   private String negocio;
    private long abaluo;
   private int kilos;
   private long preciokilo;
    @ManyToOne(fetch = FetchType.LAZY ,cascade= CascadeType.MERGE)

    private Propietario propietario;
    @OneToMany(mappedBy = "bovino")
    private List<MovimientoBovino> movimientoBovinos;
    @OneToMany(mappedBy = "bovino")
    private Set<Partos> listaPartos=new HashSet<>();
    @OneToMany(mappedBy = "bovino")
    private Set<BovinosMuertos> listaBovinosMuertos=new HashSet<>();
    @OneToMany(mappedBy = "bovino")
    private Set<Detalle_Venta> detalle_ventas = new HashSet<>();
    @OneToMany(mappedBy = "bovino")
    private Set<DetalleVentaInterna> detalle_ventas_internas = new HashSet<>();
    @OneToMany(mappedBy = "bovino")
    private List<FotoBovinos> fotoBovinos=new ArrayList<>();



}
