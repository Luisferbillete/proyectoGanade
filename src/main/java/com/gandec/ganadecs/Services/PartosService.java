package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.PartoDTO;
import com.gandec.ganadecs.DTO.PartosDTO;
import com.gandec.ganadecs.Entity.Parto;

import java.time.LocalDate;
import java.util.List;

public interface PartosService {


    /***************************************************************************************/
    void savess(PartoDTO partoDTO, String bovinoId);

    List<PartosDTO> getPartoBovino(String numeroId);

    int actualizarFechaDestete(String numeroBovino, LocalDate nuevaFechaDestete);
    PartoDTO actualizarFechaParto(String numeroBovino,PartoDTO partoDTO);

    void actualizarNombreParto(String numero, String nuevoNombre);

}
