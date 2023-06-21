package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="hierros")
@Data
public class Hierro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String figura;
    @ManyToOne(fetch = FetchType.LAZY ,cascade= CascadeType.ALL)
    @JoinColumn(name = "Propietario_id",nullable=false)
    Propietario propietario;

}
