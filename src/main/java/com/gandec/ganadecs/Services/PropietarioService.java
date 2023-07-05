package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.PropietaryComboDto;
import com.gandec.ganadecs.DTO.PropietaryDTO;

import java.util.List;

public interface PropietarioService {

    public List<PropietaryDTO> PropietaryGetAll();
    public PropietaryDTO saves (PropietaryDTO propietaryDTO);
    public void DeletePropietary(long id);
    public List<PropietaryComboDto> PROPIETARY_COMBO_DTO_LIST();



}
