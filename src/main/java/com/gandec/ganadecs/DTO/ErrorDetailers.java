package com.gandec.ganadecs.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class ErrorDetailers {
    private Date tiempo;
    private String mensaje;
    private  String detalles;

    public ErrorDetailers(Date tiempo, String mensaje, String detalles) {
        super();
        this.tiempo = tiempo;
        this.mensaje = mensaje;
        this.detalles = detalles;
    }
}
