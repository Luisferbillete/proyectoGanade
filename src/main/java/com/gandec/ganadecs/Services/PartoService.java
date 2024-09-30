package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.Parto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PartoService {
    //PartosDTO getParto(String numero);
    Optional<PartosCriasUpdate> getPartosCrias(String numero);
    Page<PartosPropietariosDTO> getPartosAll(Integer start,Integer limit);
    void save(Birthsdto birthsDTO, String bovinoId);
    void ActualizarParto(Birthsdto birthsDTO, String bovinoId);
    List<PartosPropietariosDTO> findPartosAndPropietarioWithNullDestete();
    List<PartosFindPropietarioDTO> findPartosAndPropietarioDTOWithNullDesteteAndPropietarioId(Long propietarioId);
    List<PartosPorPropietarioYCriasYSexo> FinAllPartosPorSexoCria(String sexoCria);
    List<PartosPorPropietarioYCriasYSexo> FinAllPartosPorSexoCriaYPropietary(String sexoCria, Long propietarioId);
    void deleteParto(String numero);
}
