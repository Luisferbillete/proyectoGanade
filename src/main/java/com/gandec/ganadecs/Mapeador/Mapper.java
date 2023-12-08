package com.gandec.ganadecs.Mapeador;

import com.gandec.ganadecs.DTO.*;
import com.gandec.ganadecs.Entity.MovimientoBovino;
import com.gandec.ganadecs.Entity.Propietario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {







    public List<PropietaryComboDto> entityToDTO(List<Propietario> propietarios){
      return propietarios.stream().map(this::ConvertDto).collect(Collectors.toList());
    }
   /*public List<MovimientoBovinoDTO> convertToDto(List<MovimientoBovino> movimientoBovinos){
        return movimientoBovinos.stream().map(this::convert).collect(Collectors.toList());
    }*/
    public PropietaryComboDto ConvertDto(Propietario p){
        PropietaryComboDto dto=new PropietaryComboDto();
        dto.setId(p.getId());
        dto.setFullname(p.getNombres() +" "+ p.getApellidos());
        return dto;

    }

    public MovimientoBovinoDTO convert(MovimientoBovino m){
        MovimientoBovinoDTO mov=new MovimientoBovinoDTO();



        mov.setNumero(String.valueOf(m.getBovino()));
      //  mov.setPotrero(m.getPotrero());
       mov.setFecha_de_ingreso(m.getFecha_de_ingreso());

       return mov;
   }





}
