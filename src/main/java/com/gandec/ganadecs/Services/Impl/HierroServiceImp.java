package com.gandec.ganadecs.Services.Impl;

import com.gandec.ganadecs.DTO.HierroDto;
import com.gandec.ganadecs.Entity.Hierro;
import com.gandec.ganadecs.Entity.Propietario;
import com.gandec.ganadecs.Excepciones.ResourceNotFoundExcepcion;
import com.gandec.ganadecs.Mapeador.Mappers;
import com.gandec.ganadecs.Repository.HierroRepository;
import com.gandec.ganadecs.Repository.PropietariosRepository;
import com.gandec.ganadecs.Services.HierroService;
import org.springframework.stereotype.Service;

@Service
public class HierroServiceImp implements HierroService {
    private final HierroRepository hierroRepository;
    private final PropietariosRepository propietariosRepository;
    private final Mappers mappers;

    public HierroServiceImp(HierroRepository hierroRepository, PropietariosRepository propietariosRepository, Mappers mappers) {
        this.hierroRepository = hierroRepository;
        this.propietariosRepository = propietariosRepository;
        this.mappers = mappers;
    }


    @Override
    public HierroDto save(long propietaryId, HierroDto hierroDto) {
        Propietario propietario=propietariosRepository.findById(propietaryId)
                .orElseThrow(()->new ResourceNotFoundExcepcion
                        ("Propietario","id",propietaryId));
        Hierro hierro=new Hierro();
        hierro.setFigura(hierroDto.getFigura());
        hierro.setPropietario(propietario);
        hierro=hierroRepository.save(hierro);
        hierroDto=(HierroDto)mappers.convertToDto(hierro,hierroDto);
        return hierroDto;
    }
}
