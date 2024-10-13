package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.BovinoDTO;
import com.gandec.ganadecs.DTO.Bovinos.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BovinoService {
    String saves(CreateBovino createBovino);
    String update(BovinoDTO bovinoDTO,String NumerBovino);
    Page<BovinosDTO> BovinesGetAll(Integer start, Integer limit);
    Page<BovinosGetAll> BovinosGetAll(Integer start, Integer limit);
    Page<BovinosPorPotrero> BovinosPorPotrero(Long idpotrero, Integer start, Integer limit);
    List<BovinosDTO> BovinesGetallByPropietary(long propietarioId);
    List<BovinosDTO> BovinesGetallByPropietaryAndSexo(long propietarioId,String sexo);
    List<BovinosDTO> BovinesGetallBySexo(String sexo);
    Optional<BovinosDTO> BovinesGetallByNumero(String numero);
    Optional<BovinosFindByNumero> bovinosFindByNumero(String numero);
    void DeleteBovino(String NumerBovino);



}
