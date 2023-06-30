package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.EntityDTO;
import com.gandec.ganadecs.DTO.PropietarioDTO;
import com.gandec.ganadecs.DTO.PropietarioResponse;
import com.gandec.ganadecs.DTO.PropietaryDTO;
import com.gandec.ganadecs.Entity.Propietario;
import com.gandec.ganadecs.Mapeador.Mapper;
import com.gandec.ganadecs.Mapeador.MapperList;
import com.gandec.ganadecs.Mapeador.Mappers;
import com.gandec.ganadecs.Repository.PropietariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gandec.ganadecs.Mapeador.MapperList.mapList;

@Service
public class PropietarioServiceImpl implements PropietarioService{
    @Autowired
    private PropietariosRepository propietariosRepository;
    @Autowired
    private Mapper mapper;
    @Autowired
    Mappers mappers;
    //@Override
    //@Transactional
    //public Propietario SavePropietario(Propietario propietario) {

      //  return propietariosRepository.save(propietario);
    //}

   // @Override
   // public List<Propietario> Propietario_List() {
        //return (List<Propietario>) propietariosRepository.findAll();
   // }

    @Override
    public List<PropietarioDTO> GetAll() {
        List<Propietario>propietarioList=propietariosRepository.findAll();
        return mapper.entityToDTO(propietarioList);

          }

    @Override
    public List<PropietaryDTO> PropietaryGetAll() {
        List<Propietario>propietarioList=propietariosRepository.findAll();
        return mapList(propietarioList,PropietaryDTO.class);
    }

    @Override
    public PropietarioDTO save(PropietarioDTO propietarioDTO) {
        Propietario propietario=mapper.DtoToEentity(propietarioDTO);
        propietario=propietariosRepository.save(propietario);
        return mapper.entityToDTO(propietario);
    }

    @Override
    public PropietaryDTO saves(PropietaryDTO propietaryDTO) {
        Propietario propietario=new Propietario();
        propietario= (Propietario) mappers.convertToEntity(propietaryDTO,propietario);
        propietario=propietariosRepository.save(propietario);
        propietaryDTO=(PropietaryDTO) mappers.convertToDto(propietario,propietaryDTO);
        return propietaryDTO;
    }


}
