package com.gandec.ganadecs.Mapeador;

import com.gandec.ganadecs.DTO.Propietary.PropietaryComboDto;
import com.gandec.ganadecs.Entity.Propietario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {







    public List<PropietaryComboDto> entityToDTO(List<Propietario> propietarios){
      return propietarios.stream().map(this::ConvertDto).collect(Collectors.toList());
    }

    public PropietaryComboDto ConvertDto(Propietario p){
        PropietaryComboDto dto=new PropietaryComboDto();
        dto.setId(p.getId());
        dto.setFullname(p.getNombres() +" "+ p.getApellidos());
        return dto;

    }







}
