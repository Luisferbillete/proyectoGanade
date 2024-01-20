package com.gandec.ganadecs.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PropietarioDTO  {
    private Long  id;
    private String nombres;
    private String apellidos;
    private String direccion;
    private long telefonos;
    private List<BovinoDTO>bovinoDTOList;

    }
