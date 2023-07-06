package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="bovinos")
@Data
public class Bovino {
    @Id
    String Numero;
    Date Fecha_de_nacimiento;
    Date Fecha_de_ingreso;
    String sexo;
    String color,raza;
    @ManyToOne(fetch = FetchType.LAZY ,cascade= CascadeType.ALL)
    @JoinColumn(name = "Propietario_id")
    private Propietario propietario;
    @OneToMany(mappedBy = "bovino")
    private List<MovimientoBovino> movimientoBovinos;
    @OneToMany(mappedBy = "bovino")
    private List<Parto> partos;

}
