package com.gandec.ganadecs.Services.Impl;

import com.gandec.ganadecs.DTO.Parto.*;
import com.gandec.ganadecs.Entity.Bovino;
import com.gandec.ganadecs.Entity.Partos;
import com.gandec.ganadecs.Excepciones.BovinoNotFoundException;
import com.gandec.ganadecs.Excepciones.InvalidOperationForMaleBovinoException;
import com.gandec.ganadecs.Excepciones.ResourceNotFoundExcepcion;
import com.gandec.ganadecs.Excepciones.UniqueBovinoException;
import com.gandec.ganadecs.Mapeador.Mappers;
import com.gandec.ganadecs.Repository.BovinoRepository;
import com.gandec.ganadecs.Repository.PartoRepository;
import com.gandec.ganadecs.Services.PartoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PartosServiceImpl implements PartoService {
    private final PartoRepository partoRepository;
    private final BovinoRepository bovinoRepository;
    private final Mappers mappers;



    @Override
    public Optional<PartosCriasUpdate> getPartosCrias(String numero) {
       Optional<Partos>  partos=partoRepository.findById(numero);
       if (!partos.isPresent()) {
           throw new UniqueBovinoException("la vaca numero " + numero + " no se encuantra Registrada como parida en la base de datos.");

       }

        return partoRepository.partosCriasUpdate(numero);
    }

    @Override
    public Page<PartosPropietariosDTO> getPartosAll(Integer start, Integer limit) {
        Pageable pageable= PageRequest.of(start,limit);
        return partoRepository.getPartosAll(pageable);
    }

    @Override
    public void save(Birthsdto birthsDTO, String bovinoId) {
        Bovino bovino= bovinoRepository.findById(bovinoId).orElseThrow(() ->
                new BovinoNotFoundException("Bovino con numero " + bovinoId + " no encontrado"));
        if ("Macho".equalsIgnoreCase(bovino.getSexo())) {
            throw new InvalidOperationForMaleBovinoException(
                    String.format("No se puede registrar un parto al bovino número %s porque es macho", bovinoId)
            );
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

    @Override
    public void deleteParto(String numero) {
        long NumerBovino=Long.parseLong(numero);
        Partos partos=partoRepository.findById(numero).orElseThrow(()->
                new ResourceNotFoundExcepcion("Partos","NumerBovino",NumerBovino));
        partoRepository.delete(partos);
    }
}
