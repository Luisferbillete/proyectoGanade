package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Hierros")
public class Hierro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "figuras")
    private String figura;
    @ManyToOne(fetch = FetchType.LAZY ,cascade= CascadeType.REMOVE)
    @JoinColumn(name = "Propietario_id",nullable=false)
    private Propietario propietario;
}
