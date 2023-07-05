package com.gandec.ganadecs.Services;


import com.gandec.ganadecs.DTO.PropietaryComboDto;
import com.gandec.ganadecs.DTO.PropietaryDTO;
import com.gandec.ganadecs.Entity.Propietario;
import com.gandec.ganadecs.Excepciones.ResourceNotFoundExcepcion;
import com.gandec.ganadecs.Mapeador.Mapper;
import com.gandec.ganadecs.Mapeador.Mappers;
import com.gandec.ganadecs.Repository.PropietariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.gandec.ganadecs.Mapeador.MapperList.mapList;

@Service
public class PropietarioServiceImpl implements PropietarioService{
    @Autowired
    private PropietariosRepository propietariosRepository;
    @Autowired
    private Mapper mapper;
    @Autowired
    Mappers mappers;


    @Override
    public List<PropietaryDTO> PropietaryGetAll() {
        List<Propietario>propietarioList=propietariosRepository.findAll();
        return mapList(propietarioList,PropietaryDTO.class);
    }



    @Override
    public PropietaryDTO saves(PropietaryDTO propietaryDTO) {
        Propietario propietario=new Propietario();
        propietario= (Propietario) mappers.convertToEntity(propietaryDTO,propietario);
        propietario=propietariosRepository.save(propietario);
        propietaryDTO=(PropietaryDTO) mappers.convertToDto(propietario,propietaryDTO);
        return propietaryDTO;
    }

    @Override
    public void DeletePropietary(long id) {
       Propietario propietario=propietariosRepository.findById(id).orElseThrow(()
               ->new ResourceNotFoundExcepcion("Propietario","id",id));
        ;
       propietariosRepository.delete(propietario);

    }

    @Override
    public List<PropietaryComboDto> PROPIETARY_COMBO_DTO_LIST() {
        List<Propietario> propietarioList=new ArrayList<>();
        propietarioList=propietariosRepository.findAll();
        List<PropietaryComboDto> propietaryComboDtoList=mapper.entityToDTO(propietarioList);
        return propietaryComboDtoList;

    }



}
