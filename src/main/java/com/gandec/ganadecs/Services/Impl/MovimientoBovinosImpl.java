package com.gandec.ganadecs.Services.Impl;

import com.gandec.ganadecs.DTO.MovimientoBovinoDTO;
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
import java.util.List;
import java.util.Optional;

import static com.gandec.ganadecs.Mapeador.MapperList.mapList;

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
    public void trasladar(long potreroOrig, long potreroDestino) {
        Potrero potrerodestino = potreroRepository.findById(potreroDestino).orElseThrow(() ->
                new ResourceNotFoundExcepcion("Potrero no existe", "id", potreroDestino));
        LocalDate fechaDeIngreso = LocalDate.now();
        LocalDate fechasalida = LocalDate.now();
        Date sqlCurrentDate = Date.valueOf(fechasalida); // Convert LocalDate to java.sql.Date
        List<MovimientoBovinoDTO> movimientoBovinoDTOList =
                //obtiene todos los bovinos que se encuentran en un potrero
                movimientoBovinoRepository.findMovimientoBovinoByPotrero(potreroOrig);
        //asigna la fechas de salida a los bovinos
        movimientoBovinoRepository.updateFechaDeSalida(sqlCurrentDate,potreroOrig);
        List<MovimientoBovino> movimientoBovinoList = mapList(movimientoBovinoDTOList, MovimientoBovino.class);
        for (MovimientoBovino m:movimientoBovinoList
             ) {
            MovimientoBovino movimientoBovino=new MovimientoBovino();
            movimientoBovino.setFecha_de_ingreso(fechaDeIngreso);
            movimientoBovino.setPotrero(potrerodestino);
            movimientoBovino.setBovino(m.getBovino());
            movimientoBovino.setFecha_de_salida(null);

            movimientoBovinoRepository.save(movimientoBovino);

        }
    }

    @Override
    public void trasladarBovino(String numeroId, long potreroDestino) {
        long numberConvert = Long.parseLong(numeroId);
        Potrero potrerodestino = potreroRepository.findById(potreroDestino).orElseThrow(() ->
                new ResourceNotFoundExcepcion("Potrero no existe", "id", potreroDestino));
        Bovino bovino = bovinoRepository.findById(numeroId).orElseThrow(() ->
                new ResourceNotFoundExcepcion("Bovino", "numero", numberConvert));
        LocalDate fechaDeIngreso = LocalDate.now();
        LocalDate fechasalida = LocalDate.now();
        Date sqlCurrentDate = Date.valueOf(fechasalida); // Convert LocalDate to java.sql.Date


        List<MovimientoBovinoDTO> movimientoBovinoDTOList=movimientoBovinoRepository.findMovimientoBovinoByBovino(numeroId);
        movimientoBovinoRepository.updateMovimientoBovinoByBovino(sqlCurrentDate,potreroDestino,numeroId);
        List<MovimientoBovino>movimientoBovinoLista=mapList(movimientoBovinoDTOList,MovimientoBovino.class);

        for (MovimientoBovino mb: movimientoBovinoLista
             ) {
            MovimientoBovino movimientoBovino=new MovimientoBovino();
            movimientoBovino.setFecha_de_ingreso(fechaDeIngreso);
            movimientoBovino.setPotrero(potrerodestino);
            movimientoBovino.setBovino(mb.getBovino());
            movimientoBovino.setFecha_de_salida(null);

            movimientoBovinoRepository.save(movimientoBovino);

        }

    }

    @Override
    public int probarbovino(String numeroId, long potreroDestino) {
        LocalDate fechasalida = LocalDate.now();
        Date sqlCurrentDate = Date.valueOf(fechasalida); // Convert LocalDate to java.sql.Date
        movimientoBovinoRepository.updateMovimientoBovinoByBovino(sqlCurrentDate,potreroDestino,numeroId);

        return 0;
    }


}

