package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.PartoDTO;
import com.gandec.ganadecs.DTO.PartosDTO;

import java.time.LocalDate;
import java.util.List;

public interface PartosService {


    /***************************************************************************************/
    void savess(PartoDTO partoDTO, String bovinoId);

    List<PartosDTO> getPartoBovino(String numeroId);

    int actualizarFechaDestete(String numeroBovino, LocalDate nuevaFechaDestete);


}
