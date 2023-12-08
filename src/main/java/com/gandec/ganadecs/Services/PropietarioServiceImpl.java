package com.gandec.ganadecs.Services;


import com.gandec.ganadecs.DTO.PropietarioDTO;
import com.gandec.ganadecs.DTO.PropietaryComboDto;
import com.gandec.ganadecs.DTO.PropietaryDTO;
import com.gandec.ganadecs.Entity.Propietario;
import com.gandec.ganadecs.Excepciones.ResourceNotFoundExcepcion;
import com.gandec.ganadecs.Mapeador.Mapper;
import com.gandec.ganadecs.Mapeador.Mappers;
import com.gandec.ganadecs.Repository.PropietariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gandec.ganadecs.Mapeador.MapperList.mapList;

@Service
public class PropietarioServiceImpl implements PropietarioService{
    private final PropietariosRepository propietariosRepository;
    private final Mapper mapper;
    private final Mappers  mappers;

    public PropietarioServiceImpl(PropietariosRepository propietariosRepository, Mapper mapper, Mappers mappers) {
        this.propietariosRepository = propietariosRepository;
        this.mapper = mapper;
        this.mappers = mappers;
    }


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

       propietariosRepository.delete(propietario);

    }

    @Override
    public List<PropietaryComboDto> PROPIETARY_COMBO_DTO_LIST() {
        List<Propietario> propietarioList;
        propietarioList=propietariosRepository.findAll();
        return mapper.entityToDTO(propietarioList);

    }

    @Override
    public PropietaryDTO UpdatePropietary(PropietaryDTO propietaryDTO, long id) {
        Propietario propietario=propietariosRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundExcepcion("Propietario","id",id));

        propietario.setApellidos(propietaryDTO.getApellidos());
        propietario.setNombres(propietaryDTO.getNombres());
        propietario.setDireccion(propietaryDTO.getDireccion());
        propietario.setTelefonos(propietaryDTO.getTelefonos());

        Propietario proprietaryActualize=propietariosRepository.save(propietario);
        return (PropietaryDTO) mappers.convertToDto(proprietaryActualize,propietaryDTO);
    }

    @Override
    public PropietaryDTO GetPropietary(long id) {
        PropietaryDTO dto=new PropietaryDTO();
        Propietario propietario  =propietariosRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundExcepcion
                                ("Propietario","id",id));
        return (PropietaryDTO) mappers.convertToDto(propietario,dto);
    }




}
