package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.Auth.AuthResponse;
import com.gandec.ganadecs.DTO.Auth.CreatePropietary;
import com.gandec.ganadecs.DTO.Auth.LoginRequest;
import com.gandec.ganadecs.DTO.Propietary.PropietaryGetAll;
import com.gandec.ganadecs.DTO.Propietary.PropietaryComboDto;
import com.gandec.ganadecs.DTO.Propietary.PropietaryDTO;
import com.gandec.ganadecs.Entity.ERole;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PropietarioService {
    AuthResponse login(LoginRequest request);
    CreatePropietary save(CreatePropietary createPropietary);

    //Page<PropietaryGetAll> PropietaryGetAllPage(Integer start, Integer limit);
    Page<PropietaryGetAll> PropietaryGetAll(ERole roleName, Integer start, Integer limit);
    void DeletePropietary(long id);
    List<PropietaryComboDto> getPropietary();
    PropietaryDTO UpdatePropietary(PropietaryDTO propietaryDTO,long id);
     PropietaryDTO GetPropietary(long id);
     PropietaryComboDto findPropietaryComboDtoByNombresAndApellidos(String nombres, String apellidos);





}
