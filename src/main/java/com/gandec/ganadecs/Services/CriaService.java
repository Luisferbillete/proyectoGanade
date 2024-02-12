package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.Crias.CrearCrias;
import com.gandec.ganadecs.DTO.Crias.CriasDTO;
import com.gandec.ganadecs.DTO.Crias.CriasPorParto;
import com.gandec.ganadecs.DTO.Parto.Birthsdto;

import java.time.LocalDate;
import java.util.List;

public interface CriaService {


    void save(CrearCrias crearCrias);

    void update(CriasDTO criasDTO, String numeroCria);
    void destete(LocalDate fechadeDestete, String numeroBovino);
    List<CriasPorParto> obtenerCriasPorNumeroParto(String numeroBovino);
    Birthsdto findByNumeroCria(String numeroCria);


}
