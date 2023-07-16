package com.gandec.ganadecs.Services.Impl;

import com.gandec.ganadecs.DTO.MovimientoBovinoDTO;
import com.gandec.ganadecs.Entity.MovimientoBovino;
import com.gandec.ganadecs.Mapeador.Mappers;
import com.gandec.ganadecs.Repository.MovimientoBovinoRepository;
import com.gandec.ganadecs.Services.MovimientoBovinoService;
import org.springframework.stereotype.Service;

@Service
public class MovimientoBovinosImpl implements MovimientoBovinoService {
    private final MovimientoBovinoRepository movimientoBovinoRepository;
    private  final Mappers mappers;

    public MovimientoBovinosImpl(MovimientoBovinoRepository movimientoBovinoRepository, Mappers mappers) {
        this.movimientoBovinoRepository = movimientoBovinoRepository;
        this.mappers = mappers;
    }

    @Override
    public MovimientoBovinoDTO Saves(MovimientoBovinoDTO movimientoBovinoDTO) {
        MovimientoBovino movimientoBovino=new MovimientoBovino();
        movimientoBovino= (MovimientoBovino) mappers.convertToEntity(movimientoBovinoDTO,movimientoBovino);
        movimientoBovino=movimientoBovinoRepository.save(movimientoBovino);
        movimientoBovinoDTO= (MovimientoBovinoDTO) mappers.convertToDto(movimientoBovino,movimientoBovinoDTO);
        return movimientoBovinoDTO;
    }

    @Override
    public MovimientoBovinoDTO Get(String numero ) {
        return null;
    }
}
