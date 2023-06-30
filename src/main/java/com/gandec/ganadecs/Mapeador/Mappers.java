package com.gandec.ganadecs.Mapeador;

import com.gandec.ganadecs.DTO.EntityDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mappers {
    @Autowired
    ModelMapper modelMapper;
    public EntityDTO convertToDto(Object obj,EntityDTO dto){
        return modelMapper.map(obj,dto.getClass());
    }
    public Object convertToEntity(EntityDTO dto,Object obj){
        return modelMapper.map(dto , obj.getClass());
    }

}
