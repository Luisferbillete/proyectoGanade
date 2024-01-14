package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_bovinos_muertos")
public class BovinosMuertos {
    @Id
    private String number;
    private String fecha;
    private String causa;
    private String observacion;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private Bovino bovino;
}
