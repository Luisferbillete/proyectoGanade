package com.gandec.ganadecs.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class FotoBovinos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String foto;
    private String Fotourl;
    private String idFoto;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Bovino bovino;

    public FotoBovinos(String foto, String fotourl, String idFoto, Bovino bovino) {
        this.foto = foto;
        Fotourl = fotourl;
        this.idFoto = idFoto;
        this.bovino = bovino;
    }
}
