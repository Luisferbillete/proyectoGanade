package com.gandec.ganadecs.Mapeador;

import com.gandec.ganadecs.DTO.EntityDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class Mappers {
private final  ModelMapper modelMapper;
    public EntityDTO convertToDto(Object obj,EntityDTO dto){
        return modelMapper.map(obj,dto.getClass());
    }
    public Object convertToEntity(EntityDTO dto,Object obj){
        return modelMapper.map(dto , obj.getClass());
    }

}
