package com.gandec.ganadecs.DTO.Bovinos;

import com.gandec.ganadecs.DTO.EntityDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BovinosPorPotrero implements EntityDTO {
    private String numero;
    private String nombrepotrero;


    public BovinosPorPotrero(String numero, String nombrepotrero) {
        this.numero = numero;
        this.nombrepotrero = nombrepotrero;
    }
}
