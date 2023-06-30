package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.PropietarioDTO;
import com.gandec.ganadecs.DTO.PropietarioResponse;
import com.gandec.ganadecs.DTO.PropietaryDTO;
import com.gandec.ganadecs.Entity.Propietario;

import java.security.PublicKey;
import java.util.List;

public interface PropietarioService {

    public List<PropietaryDTO> PropietaryGetAll();
    public PropietaryDTO saves (PropietaryDTO propietaryDTO);


}
