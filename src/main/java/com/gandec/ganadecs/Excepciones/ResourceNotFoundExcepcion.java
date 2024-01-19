package com.gandec.ganadecs.Excepciones;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

import static org.springframework.http.HttpStatus.NOT_FOUND;
@Getter
@Setter
@ResponseStatus(value = NOT_FOUND)
public class ResourceNotFoundExcepcion extends  RuntimeException{

    @Serial
    private  static final long serialVersionUID=1L;

    private String nombredelRecurso;
    private String nombredelCampo;
    private  long valordelCampo;
    public ResourceNotFoundExcepcion(String nombredelRecurso,String nombredelCampo, long valordelCampo){
        super(String.format("\"%s no encontrado con %s: %d\"",nombredelRecurso,nombredelCampo,valordelCampo));
        this.nombredelRecurso=nombredelRecurso;
        this.nombredelCampo=nombredelCampo;
        this.valordelCampo=valordelCampo;
    }


}
