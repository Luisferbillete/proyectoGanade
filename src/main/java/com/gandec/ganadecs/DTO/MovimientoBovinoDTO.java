package com.gandec.ganadecs.DTO;

import lombok.Data;

import java.util.Date;
@Data
public class MovimientoBovinoDTO implements EntityDTO{
    private long id;
    private String numero;
    private Date fecha_de_ingreso;
    private Date fecha_de_salida;
}
