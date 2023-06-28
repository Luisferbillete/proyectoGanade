package com.gandec.ganadecs.Mapeador;

import com.gandec.ganadecs.DTO.EntityDTO;
import com.gandec.ganadecs.DTO.PropietarioDTO;
import com.gandec.ganadecs.Entity.Propietario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {
    ModelMapper modelMapper=new ModelMapper();
   //   ModelMapper mapper = new ModelMapper();
     //   return mapper.map(obj,entityDTO.getClass());
   // }
    //public  Object ConvertToEntity(EntityDTO entityDTO,Object obj){
      //  ModelMapper mapper=new ModelMapper();
        //return mapper.map(entityDTO,obj.getClass());
    //}
    public PropietarioDTO entityToDTO(Propietario propietario) {
       // ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(propietario,PropietarioDTO.class);
    }
    public List<PropietarioDTO> entityToDTO(List<Propietario> propietarios){
        return propietarios.stream().map(this::entityToDTO).collect(Collectors.toList());
    }
    public Propietario DtoToEentity(PropietarioDTO propietarioDTO){
        return modelMapper.map(propietarioDTO,Propietario.class);
    }
    public  List<Propietario> DtoToEentity(List<PropietarioDTO>propietarioDTOList){
        return propietarioDTOList.stream().map(this::DtoToEentity).collect(Collectors.toList());
    }

}
