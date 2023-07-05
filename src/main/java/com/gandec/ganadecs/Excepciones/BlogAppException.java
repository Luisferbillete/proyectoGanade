package com.gandec.ganadecs.Excepciones;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class BlogAppException extends RuntimeException{
    private HttpStatus estado;
    private String mensaje;




    public BlogAppException(HttpStatus estado, String mensaje) {
        super();
        this.estado = estado;
        this.mensaje = mensaje;
    }

}
