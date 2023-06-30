package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.PropietarioDTO;
import com.gandec.ganadecs.DTO.PropietarioResponse;
import com.gandec.ganadecs.DTO.PropietaryDTO;
import com.gandec.ganadecs.Entity.Propietario;

import java.security.PublicKey;
import java.util.List;

public interface PropietarioService {
    //public Propietario SavePropietario(Propietario propietario);
    //public List<Propietario> Propietario_List();
    public List<PropietarioDTO> GetAll();
    public List<PropietaryDTO> PropietaryGetAll();
    public PropietarioDTO save(PropietarioDTO propietarioDTO);
    public PropietaryDTO saves (PropietaryDTO propietaryDTO);


}
