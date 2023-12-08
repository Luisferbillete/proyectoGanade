package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.MovimientoBovinoDTO;

import com.gandec.ganadecs.DTO.MovimientosDTO;


import java.util.List;

public interface MovimientoBovinoService {
    List<MovimientosDTO>getAllBovinosBypaddock();

     List<MovimientoBovinoDTO>  findByMovimientosBovino(String numero);
    List<MovimientoBovinoDTO> getMovimientoBovinosPorPotrero(long potrero);


     void trasladar(long potreroOrig ,long potreroDestino);
    void trasladarBovino(String numeroId,long potreroDestino);


}
