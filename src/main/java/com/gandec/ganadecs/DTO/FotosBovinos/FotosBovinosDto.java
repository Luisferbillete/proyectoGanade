package com.gandec.ganadecs.DTO.FotosBovinos;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FotosBovinosDto {
    private long id;
    private String foto;
    private String Fotourl;
    private String idFoto;
    private String bovino;

    public FotosBovinosDto(long id, String foto, String fotourl, String idFoto, String bovino) {
        this.id = id;
        this.foto = foto;
        Fotourl = fotourl;
        this.idFoto = idFoto;
        this.bovino = bovino;
    }
}
