package com.gandec.ganadecs.Mapeador;

import com.gandec.ganadecs.DTO.EntityDTO;
import com.gandec.ganadecs.DTO.PropietarioDTO;
import com.gandec.ganadecs.DTO.PropietaryComboDto;
import com.gandec.ganadecs.Entity.Propietario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {


   // private List<Propietario> propietarios=new ArrayList<Propietario>();public PropietaryComboDto ConvertDto(){
    //        Propietario p=new Propietario();
    //        PropietaryComboDto dto=new PropietaryComboDto();
    //        dto.setId(p.getId());
    //        dto.setFullname(p.getNombres()+p.getApellidos());
    //        return dto;
    //
    //    }

    //ModelMapper modelMapper=new ModelMapper();
   //   ModelMapper mapper = new ModelMapper();
     //   return mapper.map(obj,entityDTO.getClass());
   // }
    //public  Object ConvertToEntity(EntityDTO entityDTO,Object obj){
      //  ModelMapper mapper=new ModelMapper();
        //return mapper.map(entityDTO,obj.getClass());
    //}
    //public PropietarioDTO entityToDTO(Propietario propietario) {
       // ModelMapper modelMapper = new ModelMapper();
      //  return modelMapper.map(propietario,PropietarioDTO.class);
    //}
    public List<PropietaryComboDto> entityToDTO(List<Propietario> propietarios){
      return propietarios.stream().map(this::ConvertDto).collect(Collectors.toList());
    }
    public PropietaryComboDto ConvertDto(Propietario p){
        PropietaryComboDto dto=new PropietaryComboDto();
        dto.setId(p.getId());
        dto.setFullname(p.getNombres() +" "+ p.getApellidos());
        return dto;

    }


    //}
    //public Propietario DtoToEentity(PropietarioDTO propietarioDTO){
      //  return modelMapper.map(propietarioDTO,Propietario.class);
    //}
    //public  List<Propietario> DtoToEentity(List<PropietarioDTO>propietarioDTOList){
      //  return propietarioDTOList.stream().map(this::DtoToEentity).collect(Collectors.toList());

    //}


}
