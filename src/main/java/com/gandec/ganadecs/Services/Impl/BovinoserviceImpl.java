package com.gandec.ganadecs.Services.Impl;

import com.gandec.ganadecs.DTO.BovinoDTO;
import com.gandec.ganadecs.Entity.Bovino;
import com.gandec.ganadecs.Mapeador.Mappers;
import com.gandec.ganadecs.Repository.BovinoRepository;
import com.gandec.ganadecs.Services.BovinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BovinoserviceImpl implements BovinoService {
    @Autowired
    BovinoRepository bovinoRepository;
    @Autowired
    Mappers mappers;
    @Override
    public BovinoDTO save(BovinoDTO bovinoDTO) {
        Bovino bovino=new Bovino();
        bovino= (Bovino) mappers.convertToEntity(bovinoDTO,bovino);
        bovino=bovinoRepository.save(bovino);
        bovinoDTO= (BovinoDTO) mappers.convertToDto(bovino,bovinoDTO);
        return bovinoDTO;
    }

    @Override
    public List<BovinoDTO> BOVINO_DTO_LIST() {
        return null;
    }

    @Override
    public BovinoDTO GetBovino(String number) {
        return null;
    }

    @Override
    public BovinoDTO UpdateBovino(BovinoDTO bovinoDTO, String number) {
        return null;
    }

    @Override
    public void DeleteBovino(String number) {

    }
}
