package com.gandec.ganadecs.DTO.Parto;

import com.gandec.ganadecs.DTO.EntityDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PartosPropietariosDTO implements EntityDTO {
    private long propietarioId;
    private String propietarioNombre;
    private String propietarioApellido;
    private String partosNumero;
    private String partosnombre;
    private LocalDate partosFechaDeParto;


    public PartosPropietariosDTO(long propietarioId, String propietarioNombre, String propietarioApellido,
                                 String partosNumero, String partosnombre, LocalDate partosFechaDeParto) {
        this.propietarioId = propietarioId;
        this.propietarioNombre = propietarioNombre;
        this.propietarioApellido = propietarioApellido;
        this.partosNumero = partosNumero;
        this.partosnombre = partosnombre;
        this.partosFechaDeParto = partosFechaDeParto;
    }
}
