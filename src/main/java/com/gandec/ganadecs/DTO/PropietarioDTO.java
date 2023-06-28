package com.gandec.ganadecs.DTO;

import com.gandec.ganadecs.Entity.Bovino;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropietarioDTO  {
    private Integer  id;
    private String nombres;
    private String apellidos;
    private String direccion;
    private Integer telefono;
    private List<BovinoDTO>bovinoDTOList;



}
