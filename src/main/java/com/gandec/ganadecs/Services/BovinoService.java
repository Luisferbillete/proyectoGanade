package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.BovinoDTO;
import com.gandec.ganadecs.DTO.Bovinos.BovinosDTO;
import com.gandec.ganadecs.DTO.Bovinos.CreateBovino;
import java.util.List;
import java.util.Optional;

public interface BovinoService {
    String savess(CreateBovino createBovino);
    String save(long propietarioid,long potreroId, BovinoDTO bovinoDTO);
    String update(BovinoDTO bovinoDTO,String NumerBovino);
    List<BovinosDTO> BovinesGetAll();
    List<BovinosDTO> BovinesGetallByPropietary(long propietarioId);
    List<BovinosDTO> BovinesGetallByPropietaryAndSexo(long propietarioId,String sexo);
    List<BovinosDTO> BovinesGetallBySexo(String sexo);
    Optional<BovinosDTO> BovinesGetallByNumero(String numero);





}
