package com.gandec.ganadecs.Services.Impl;

import com.gandec.ganadecs.DTO.PartoDTO;
import com.gandec.ganadecs.DTO.PartosDTO;
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
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PartoServiceImpl implements PartosService {
    private final PartosRepository partosRepository;
    private final BovinoRepository bovinoRepository;
    private final CriaRepository criaRepository;
    private final Mappers mappers;


    @Override
    public void savess(PartoDTO partoDTO, String bovinoId) {


        long counPartos = partosRepository.countPartosByBovinoNumeroId(bovinoId) + 1;
        Parto parto = new Parto();
        List<PartoDTO> partoDTOList = partosRepository.findByNumeroBovinoAndFechaDesteteIsNull(bovinoId);

        parto = (Parto) mappers.convertToEntity(partoDTO, parto);
        long numberConvert = Long.parseLong(bovinoId);


        if (partoDTOList.size() >= 1) {
            throw new IllegalStateException("El bovino con numero " + numberConvert +
                    " ya tiene un parto sin destetar.");
        }
        Bovino bovino = bovinoRepository.findById(bovinoId).orElseThrow(() ->
                new ResourceNotFoundExcepcion("Bovino",
                        "numero  " + numberConvert, numberConvert));
        parto.setBovino(bovino);
        if (bovino.getSexo().equals("hembra")) {


            parto = partosRepository.save(parto);

            Cria cria = new Cria();
            cria.setNum(bovinoId + counPartos);
            cria.setNumero(bovinoId);
            cria.setSexo("Hembra");
            cria.setParto(parto);


            criaRepository.save(cria);
        } else {
            throw new IllegalStateException("El bovino con numero " + numberConvert + " no es hembra.");
        }

    }

    @Override
    public List<PartosDTO> getPartoBovino(String numeroId) {
        return partosRepository.findPartosByFechaNullAndBovinoNumeroId(numeroId);
    }

    @Override
    public int actualizarFechaDestete(String numeroBovino, LocalDate nuevaFechaDestete) {

        return partosRepository.actualizarFechaDestete(numeroBovino, nuevaFechaDestete);
    }

    @Override
    public PartoDTO actualizarFechaParto(String numeroBovino, PartoDTO partoDTO) {
        long numero = Long.parseLong(numeroBovino);
        Parto parto = partosRepository.findByNumeroParto(numeroBovino).orElseThrow(() ->
                new ResourceNotFoundExcepcion("Parto",
                        "numero  " + numeroBovino, numero));
        parto.setNombre(partoDTO.getNombre());
        parto.setFecha_de_parto(partoDTO.getFecha_de_parto());

        partosRepository.save(parto);
        partoDTO = (PartoDTO) mappers.convertToDto(parto, partoDTO);
        return partoDTO;
    }



    @Override
    public void actualizarNombreParto(String numero, String nuevoNombre) {
        Parto parto = partosRepository.findByNumeroParto(numero).orElseThrow(() ->
                new ResourceNotFoundExcepcion("Parto",
                        "numero  " + numero, Long.parseLong(numero)));
        partosRepository.actualizarNombreParto(numero, nuevoNombre);
    }


}







