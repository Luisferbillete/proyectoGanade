package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.Auth.AuthResponse;
import com.gandec.ganadecs.DTO.Auth.CreatePropietary;
import com.gandec.ganadecs.DTO.Auth.LoginRequest;
import com.gandec.ganadecs.DTO.PropietaryComboDto;
import com.gandec.ganadecs.DTO.PropietaryDTO;

import java.util.List;

public interface PropietarioService {
    AuthResponse login(LoginRequest request);
    CreatePropietary save(CreatePropietary createPropietary);

    List<PropietaryDTO> PropietaryGetAll();

    void DeletePropietary(long id);
    List<PropietaryComboDto> PROPIETARY_COMBO_DTO_LIST();
    PropietaryDTO UpdatePropietary(PropietaryDTO propietaryDTO,long id);
     PropietaryDTO GetPropietary(long id);
     PropietaryComboDto findPropietaryComboDtoByNombresAndApellidos(String nombres, String apellidos);





}
