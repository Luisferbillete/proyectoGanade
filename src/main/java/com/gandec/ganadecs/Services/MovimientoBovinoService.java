package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.MovimientoBovinoDTO;

public interface MovimientoBovinoService {
    public MovimientoBovinoDTO Saves(MovimientoBovinoDTO movimientoBovinoDTO);
    public MovimientoBovinoDTO Get( String numero);

}
