package com.gandec.ganadecs.Services.Impl;

import com.gandec.ganadecs.DTO.Parto.Birthsdto;
import com.gandec.ganadecs.DTO.Parto.PartosFindPropietarioDTO;
import com.gandec.ganadecs.DTO.Parto.PartosPorPropietarioYCriasYSexo;
import com.gandec.ganadecs.DTO.Parto.PartosPropietariosDTO;
import com.gandec.ganadecs.Entity.Bovino;
import com.gandec.ganadecs.Entity.Partos;
import com.gandec.ganadecs.Mapeador.Mappers;
import com.gandec.ganadecs.Repository.BovinoRepository;
import com.gandec.ganadecs.Repository.PartoRepository;
import com.gandec.ganadecs.Services.PartoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartosServiceImpl implements PartoService {
    private final PartoRepository partoRepository;
    private final BovinoRepository bovinoRepository;
    private final Mappers mappers;


    @Override
    public void save(Birthsdto birthsDTO, String bovinoId) {
        Bovino bovino= bovinoRepository.findById(bovinoId).orElseThrow(() ->
                new RuntimeException("Bovino no encontrado"));
        if (bovino.getSexo().equals("Macho")){
            throw new RuntimeException("No se puede registrar un parto al bovino numero " + bovinoId +
                    " porque es macho");

        }
        Partos partos=new Partos();
        partos=(Partos) mappers.convertToEntity(birthsDTO,partos);
        partos.setNumero(bovinoId);
        partos.setBovino(bovino);

        partoRepository.save(partos);

    }

    @Override
    public void ActualizarParto(Birthsdto birthsDTO, String bovinoId) {
        Bovino bovino= bovinoRepository.findById(bovinoId).orElseThrow(() ->
                new RuntimeException("Bovino no encontrado"));
        Partos partos;
         partos=partoRepository.findById(bovinoId).orElseThrow(() ->
                new RuntimeException("Parto no encontrado"));
         if (partos!=null){
             Partos parto=new Partos();
            parto = (Partos) mappers.convertToEntity(birthsDTO, parto);
            parto.setNumero(bovinoId);
            parto.setNombre(birthsDTO.getNombre());
            parto.setBovino(bovino);
            partoRepository.save(parto);
        }else {
            throw new RuntimeException("Parto no encontrado");
        }
    }


    @Override
    public List<PartosPropietariosDTO> findPartosAndPropietarioWithNullDestete() {
    return partoRepository.findPartosAndPropietarioWithNullDestete();}

    @Override
    public List<PartosFindPropietarioDTO> findPartosAndPropietarioDTOWithNullDesteteAndPropietarioId(Long propietarioId) {
    return partoRepository.findPartosAndPropietarioDTOWithNullDesteteAndPropietarioId(propietarioId);}


    @Override
    public List<PartosPorPropietarioYCriasYSexo> FinAllPartosPorSexoCria(String sexoCria) {
    return partoRepository.FinAllPartosPorSexoCria(sexoCria);}

    @Override
    public List<PartosPorPropietarioYCriasYSexo> FinAllPartosPorSexoCriaYPropietary(String sexoCria, Long propietarioId) {
    return partoRepository.FinAllPartosPorSexoCriaYPropietary(sexoCria,propietarioId);}
}
