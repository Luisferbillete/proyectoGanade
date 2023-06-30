package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.PropietaryDTO;

import java.util.List;

public interface PropietarioService {

    public List<PropietaryDTO> PropietaryGetAll();
    public PropietaryDTO saves (PropietaryDTO propietaryDTO);


}
