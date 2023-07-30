package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.MovimientoBovinoDTO;

import com.gandec.ganadecs.Entity.MovimientoBovino;
import com.gandec.ganadecs.Entity.Potrero;

import java.time.LocalDate;
import java.util.List;

public interface MovimientoBovinoService {
    public MovimientoBovinoDTO Saves(MovimientoBovinoDTO movimientoBovinoDTO);

    public List<MovimientoBovinoDTO> getMovimientos(Potrero potrero);
    public List<MovimientoBovinoDTO>  findByMovimientosBovino(String numero);
    public List<MovimientoBovinoDTO> getMovimientoBovinosPorPotrero(long potrero);
    public int UpdateBovinosPotrero(Potrero potrero);
    public  int updateFechaDeSalida(Long potrero);
    public List<MovimientoBovinoDTO> TraladoPotter(long potreroId);
    public List<MovimientoBovino>convertirEntity(long potreroId);

    public  void trasladar(long potreroOrig ,long potreroDestino);
    public void trasladarBovino(String numeroId,long potreroDestino);
    int probarbovino(String numeroId);


}
