package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Hierro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String figura;

    private String HierroUrl;

    private String HierroId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)

    private Propietario propietario;

    public Hierro(String figura, String hierroUrl, String hierroId, Propietario propietario) {
        this.figura = figura;
        HierroUrl = hierroUrl;
        HierroId = hierroId;
        this.propietario = propietario;
    }
}
