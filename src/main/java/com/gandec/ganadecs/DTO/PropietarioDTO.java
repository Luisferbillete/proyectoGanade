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
    private Integer telefono;
    private List<BovinoDTO>bovinoDTOList;

    public PropietarioDTO(Long id, String nombres, String apellidos, String direccion, Integer telefono, List<BovinoDTO> bovinoDTOList) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.bovinoDTOList = bovinoDTOList;
    }

    public PropietarioDTO(Long id, String nombres, String apellidos) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }
}
