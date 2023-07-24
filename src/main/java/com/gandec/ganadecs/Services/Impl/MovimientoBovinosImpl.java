package com.gandec.ganadecs.Services.Impl;

import com.gandec.ganadecs.DTO.MovimientoBovinoDTO;
import com.gandec.ganadecs.DTO.PotreroDto;
import com.gandec.ganadecs.Entity.Bovino;
import com.gandec.ganadecs.Entity.MovimientoBovino;
import com.gandec.ganadecs.Entity.Potrero;
import com.gandec.ganadecs.Excepciones.ResourceNotFoundExcepcion;
import com.gandec.ganadecs.Mapeador.Mappers;
import com.gandec.ganadecs.Repository.BovinoRepository;
import com.gandec.ganadecs.Repository.MovimientoBovinoRepository;
import com.gandec.ganadecs.Repository.PotreroRepository;
import com.gandec.ganadecs.Services.MovimientoBovinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.gandec.ganadecs.Mapeador.MapperList.mapList;
import static com.gandec.ganadecs.Mapeador.MapperList.potreroToLong;

@Service
@RequiredArgsConstructor
public class MovimientoBovinosImpl implements MovimientoBovinoService {
    private final PotreroRepository potreroRepository;
    private final MovimientoBovinoRepository movimientoBovinoRepository;
    private final BovinoRepository bovinoRepository;
    private final Mappers mappers;


    @Override
    public MovimientoBovinoDTO Saves(MovimientoBovinoDTO movimientoBovinoDTO) {
        MovimientoBovino movimientoBovino = new MovimientoBovino();
        movimientoBovino = (MovimientoBovino) mappers.convertToEntity(movimientoBovinoDTO, movimientoBovino);
        movimientoBovino = movimientoBovinoRepository.save(movimientoBovino);
        movimientoBovinoDTO = (MovimientoBovinoDTO) mappers.convertToDto(movimientoBovino, movimientoBovinoDTO);
        return movimientoBovinoDTO;
    }

    @Override
    public List<MovimientoBovinoDTO> getMovimientos(Potrero potrero) {

        return movimientoBovinoRepository.findMovimientoBovinoByPotreroAndFecha_de_salidaIsNull(potrero);
    }

    @Override
    public List<MovimientoBovinoDTO> findByMovimientosBovino(String numero) {
        long numberConvert = Long.parseLong(numero);
        Bovino bovino = bovinoRepository.findById(numero).orElseThrow(() ->
                new ResourceNotFoundExcepcion("Bovino", "numero", numberConvert));
        return movimientoBovinoRepository.findMovimientoBovinoByBovino(numero);
    }

    @Override
    public List<MovimientoBovinoDTO> getMovimientoBovinosPorPotrero(long potrero) {
        Potrero potter = potreroRepository.findById(potrero).orElseThrow(() ->
                new ResourceNotFoundExcepcion("Potrero", "id", potrero));
        return movimientoBovinoRepository.todo(potrero);
    }

    @Override
    public int UpdateBovinosPotrero(Potrero potrero) {
        LocalDate fechasalida = LocalDate.now();
        return movimientoBovinoRepository.UpdateMovimientobovinosFechaSalida(potrero, fechasalida);
    }

    @Override
    public int updateFechaDeSalida(Long potrero) {
        LocalDate fechasalida = LocalDate.now();
        Date sqlCurrentDate = Date.valueOf(fechasalida); // Convert LocalDate to java.sql.Date

        return movimientoBovinoRepository.updateFechaDeSalida(sqlCurrentDate, potrero);
    }

    @Override
    public List<MovimientoBovinoDTO> TraladoPotter(long potreroId) {
        return movimientoBovinoRepository.findMovimientoBovinoByPotrero(potreroId);
    }

    @Override
    public List<MovimientoBovino> convertirEntity(long potreroId) {
        List<MovimientoBovinoDTO> movimientoBovinoDTOList =
                movimientoBovinoRepository.findMovimientoBovinoByPotrero(potreroId);
        return mapList(movimientoBovinoDTOList, MovimientoBovino.class);
    }

    @Override
    public List<MovimientoBovinoDTO> saveTrasladoBovinos(Long potreroOrigen, long potreroDestino) {
        Potrero potrerodestino = potreroRepository.findById(potreroDestino).orElseThrow(() ->
                new ResourceNotFoundExcepcion("Potrero", "id", potreroDestino));
        Potrero potrerOrigen = potreroRepository.findById(potreroOrigen).orElseThrow(() ->
                new ResourceNotFoundExcepcion("Potrero", "id", potreroOrigen));
        //long potreroId = potreroToLong(potreroOrigen);
        LocalDate fechasalida = LocalDate.now();

        Date sqlCurrentDate = Date.valueOf(fechasalida); // Convert LocalDate to java.sql.Date
        List<MovimientoBovino> movimientoBovinoList;
        List<MovimientoBovinoDTO> movimientoBovinoDTOList =
                movimientoBovinoRepository.findMovimientoBovinoByPotrero(potreroOrigen);
        movimientoBovinoRepository.updateFechaDeSalida(sqlCurrentDate, potreroOrigen);

        movimientoBovinoList = mapList(movimientoBovinoDTOList, MovimientoBovino.class);
        LocalDate fechaDeIngreso = LocalDate.now();

        for (MovimientoBovino movimientobovinos : movimientoBovinoList) {
            movimientobovinos.setPotrero(potrerodestino);
            movimientobovinos.setFecha_de_ingreso(fechaDeIngreso);
            movimientobovinos.setBovino(movimientobovinos.getBovino());
            movimientobovinos.setFecha_de_salida(null);
            movimientoBovinoRepository.save(movimientobovinos);

        }
        return movimientoBovinoDTOList;
    }


}
   /* @Override
    public List<MovimientoBovinoDTO> saveTrasladoBovinos(long potreroOrigen, long potreroDestino) {
        Potrero potrero = potreroRepository.findById(potreroDestino).orElseThrow(() ->
                new ResourceNotFoundExcepcion("Potrero", "id", potreroDestino));
        Potrero potrerOrigen = potreroRepository.findById(potreroOrigen).orElseThrow(() ->
                new ResourceNotFoundExcepcion("Potrero", "id", potreroOrigen));
        //long potreroId = potreroToLong(potreroOrigen);
        LocalDate fechasalida = LocalDate.now();

        Date sqlCurrentDate = Date.valueOf(fechasalida); // Convert LocalDate to java.sql.Date

        movimientoBovinoRepository.updateFechaDeSalida(sqlCurrentDate, potreroOrigen);
        List<MovimientoBovino> movimientoBovinoList;
        List<MovimientoBovinoDTO> movimientoBovinoDTOList =
                movimientoBovinoRepository.findMovimientoBovinoByPotrero(potreroOrigen);
        movimientoBovinoList = mapList(movimientoBovinoDTOList, MovimientoBovino.class);
        LocalDate fechaDeIngreso = LocalDate.now();

        for (MovimientoBovino movimientobovinos : movimientoBovinoList) {
            movimientobovinos.setPotrero(potrero);
            movimientobovinos.setFecha_de_ingreso(fechaDeIngreso);
            movimientobovinos.setBovino(movimientobovinos.getBovino());
            movimientoBovinoRepository.save(movimientobovinos);

        }


    }*/

