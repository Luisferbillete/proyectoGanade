package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BovinosMuertos {
    @Id
    private String number;
    private String fecha;
    private String causa;
    private String observacion;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    private Bovino bovino;
}
