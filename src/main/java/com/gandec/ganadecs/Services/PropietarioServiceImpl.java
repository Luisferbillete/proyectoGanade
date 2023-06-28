package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.PropietarioDTO;
import com.gandec.ganadecs.DTO.PropietarioResponse;
import com.gandec.ganadecs.Entity.Propietario;
import com.gandec.ganadecs.Mapeador.Mapper;
import com.gandec.ganadecs.Repository.PropietariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PropietarioServiceImpl implements PropietarioService{
    @Autowired
    private PropietariosRepository propietariosRepository;
    @Autowired
    private Mapper mapper;
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
    public PropietarioDTO save(PropietarioDTO propietarioDTO) {
        Propietario propietario=mapper.DtoToEentity(propietarioDTO);
        propietario=propietariosRepository.save(propietario);
        return mapper.entityToDTO(propietario);
    }


}
