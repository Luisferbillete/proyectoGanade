package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.Parto.Birthsdto;
import com.gandec.ganadecs.DTO.Parto.PartosFindPropietarioDTO;
import com.gandec.ganadecs.DTO.Parto.PartosPorPropietarioYCriasYSexo;
import com.gandec.ganadecs.DTO.Parto.PartosPropietariosDTO;

import java.util.List;

public interface PartoService {

    void save(Birthsdto birthsDTO, String bovinoId);
    void ActulizarParto(Birthsdto birthsDTO, String bovinoId);

    List<PartosPropietariosDTO> findPartosAndPropietarioWithNullDestete();
    List<PartosFindPropietarioDTO> findPartosAndPropietarioDTOWithNullDesteteAndPropietarioId(Long propietarioId);

    List<PartosPorPropietarioYCriasYSexo> FinAllPartosPorSexoCria(String sexoCria);
    List<PartosPorPropietarioYCriasYSexo> FinAllPartosPorSexoCriaYPropietary(String sexoCria, Long propietarioId);
}
