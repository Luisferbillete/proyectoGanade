package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.PropietarioDTO;
import com.gandec.ganadecs.DTO.PropietarioResponse;
import com.gandec.ganadecs.Entity.Propietario;

import java.util.List;

public interface PropietarioService {
    //public Propietario SavePropietario(Propietario propietario);
    //public List<Propietario> Propietario_List();
    public List<PropietarioDTO> GetAll();
    public PropietarioDTO save(PropietarioDTO propietarioDTO);


}
