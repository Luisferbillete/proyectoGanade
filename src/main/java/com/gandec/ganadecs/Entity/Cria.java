package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="crias")
@Data
public class Cria {
    @Id
    private String num;
    private String numero;
    private String sexo;
    @ManyToOne(fetch = FetchType.LAZY ,cascade= CascadeType.ALL)
    @JoinColumn(name = "Cria_num",nullable=false)
    private Parto parto;
}
