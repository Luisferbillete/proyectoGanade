package com.gandec.ganadecs.Services.Impl;

import com.gandec.ganadecs.DTO.PartoDTO;
import com.gandec.ganadecs.Entity.Bovino;
import com.gandec.ganadecs.Entity.Cria;
import com.gandec.ganadecs.Entity.Parto;
import com.gandec.ganadecs.Excepciones.ResourceNotFoundExcepcion;
import com.gandec.ganadecs.Mapeador.Mappers;
import com.gandec.ganadecs.Repository.BovinoRepository;
import com.gandec.ganadecs.Repository.CriaRepository;
import com.gandec.ganadecs.Repository.PartosRepository;
import com.gandec.ganadecs.Services.PartosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PartoServiceImpl implements PartosService {
    private final PartosRepository partosRepository;
    private final BovinoRepository bovinoRepository;
    private final CriaRepository criaRepository;
    private final Mappers mappers;
    @Override
    public PartoDTO save(PartoDTO partoDTO, String bovinoId) {
        Parto parto=new Parto();
        parto= (Parto) mappers.convertToEntity(partoDTO,parto);

        long numberConvert = Long.parseLong(bovinoId);

        Bovino bovino = bovinoRepository.findById(bovinoId).orElseThrow(() ->
                new ResourceNotFoundExcepcion("Bovino", "numero  " + numberConvert, numberConvert));
        parto.setBovino(bovino);
        parto=partosRepository.save(parto);

        partoDTO= (PartoDTO) mappers.convertToDto(parto,partoDTO);

        return partoDTO;
    }

    @Override
    public Parto saves(Parto parto,Bovino bovino) {

        parto.setBovino(bovino);
        System.out.println("fgfgfg"+parto.getFecha_de_parto());
         partosRepository.save(parto);

        return parto;
    }

    @Override
    public void savess(PartoDTO partoDTO, String bovinoId) {
        long counPartos= partosRepository.countPartosByBovinoNumeroId(bovinoId)+1;
        Parto parto=new Parto();
        parto= (Parto) mappers.convertToEntity(partoDTO,parto);
        long numberConvert = Long.parseLong(bovinoId);
        Bovino bovino = bovinoRepository.findById(bovinoId).orElseThrow(() ->
                new ResourceNotFoundExcepcion("Bovino", "numero  " + numberConvert, numberConvert));
        parto.setBovino(bovino);
        parto=partosRepository.save(parto);

        Cria cria=new Cria();
        cria.setNum(bovinoId + counPartos);
        cria.setNumero(bovinoId);
        cria.setSexo("Hembra");
        cria.setParto(parto);


        criaRepository.save(cria);


    }


}
