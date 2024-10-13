package com.gandec.ganadecs.Services.Impl;

import com.gandec.ganadecs.DTO.MovimientoBovinoDTO;
import com.gandec.ganadecs.DTO.MovimientosDTO;
import com.gandec.ganadecs.Entity.Bovino;
import com.gandec.ganadecs.Entity.MovimientoBovino;
import com.gandec.ganadecs.Entity.Potrero;
import com.gandec.ganadecs.Excepciones.EmptyListExcepcion;
import com.gandec.ganadecs.Excepciones.ResourceNotFoundExcepcion;
import com.gandec.ganadecs.Repository.BovinoRepository;
import com.gandec.ganadecs.Repository.MovimientoBovinoRepository;
import com.gandec.ganadecs.Repository.PotreroRepository;
import com.gandec.ganadecs.Services.MovimientoBovinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static com.gandec.ganadecs.Mapeador.MapperList.mapList;

@Service
@RequiredArgsConstructor
public class MovimientoBovinosImpl implements MovimientoBovinoService {
    private final PotreroRepository potreroRepository;
    private final MovimientoBovinoRepository movimientoBovinoRepository;
    private final BovinoRepository bovinoRepository;



    @Override
    public List<MovimientosDTO> getAllBovinosBypaddock() {
        return movimientoBovinoRepository.getAllBovinespaddock();
    }

    @Override
    public List<MovimientoBovinoDTO> findByMovimientosBovino(String numero) {
        long numberConvert = Long.parseLong(numero);
        bovinoRepository.findById(numero).orElseThrow(() ->
                new ResourceNotFoundExcepcion("Bovino", "numero :", numberConvert));
        return movimientoBovinoRepository.findMovimientoBovinoByBovino(numero);
    }

    @Override
    public List<MovimientoBovinoDTO> getMovimientoBovinosPorPotrero(long potrero) {
        potreroRepository.findById(potrero).orElseThrow(() ->
                new ResourceNotFoundExcepcion("Potrero", "id", potrero));
        return movimientoBovinoRepository.todo(potrero);
    }

    @Override
    public void trasladar(long potreroOrig, long potreroDestino) {
        Potrero potrerodestino = potreroRepository.findById(potreroDestino).orElseThrow(() ->
                new ResourceNotFoundExcepcion("Potrero no existe", "id : "
                        + potreroDestino, potreroDestino));
        Potrero potreroorig = potreroRepository.findById(potreroOrig).orElseThrow(() ->
                new ResourceNotFoundExcepcion("Potrero no existe", "id : "
                        + potreroOrig, potreroOrig));
        String nombrePotrero= potreroorig.getNombre();
        System.out.println(nombrePotrero);
        LocalDate fechaDeIngreso = LocalDate.now();
        LocalDate fechasalida = LocalDate.now();
        Date sqlCurrentDate = Date.valueOf(fechasalida); // Convert LocalDate to java.sql.Date
 //obtiene todos los bovinos que se encuentran en un potrero
        List<MovimientoBovinoDTO> movimientoBovinoDTOList =
                movimientoBovinoRepository.findMovimientoBovinoByPotrero(potreroOrig);
        if (movimientoBovinoDTOList.isEmpty()) {
            throw new EmptyListExcepcion("Se encuentra vacio el potrero : ",
            " potrero "+ nombrePotrero);

        }else {
            //asigna la fechas de salida a los bovinos
            movimientoBovinoRepository.updateFechaDeSalida(sqlCurrentDate, potreroOrig);
            convertSaveMovimientoBovino(potrerodestino, fechaDeIngreso, movimientoBovinoDTOList);
        }
    }

    @Override
    public void trasladarBovino(String numeroId, long potreroDestino) {
        long numberConvert = Long.parseLong(numeroId);
        bovinoRepository.findById(numeroId).orElseThrow(() ->
                new ResourceNotFoundExcepcion("Bovino", "numero  " + numberConvert, numberConvert));
        Potrero potrerodestino = potreroRepository.findById(potreroDestino).orElseThrow(() ->
                new ResourceNotFoundExcepcion("Potrero", "id " + potreroDestino,potreroDestino));

        LocalDate fechaDeIngreso = LocalDate.now();
        LocalDate fechasalida = LocalDate.now();
        Date sqlCurrentDate = Date.valueOf(fechasalida); // Convert LocalDate to java.sql.Date


        List<MovimientoBovinoDTO> movimientoBovinoDTOList=movimientoBovinoRepository.findMovimientoBovinoByBovino(numeroId);
        movimientoBovinoRepository.updateMovimientoBovinoByBovino(sqlCurrentDate,numeroId);
        convertSaveMovimientoBovino(potrerodestino, fechaDeIngreso, movimientoBovinoDTOList);

    }
//traslado mas eficente
    @Override
    public void trasladoDeBovinos(long potreroOrig, long potreroDestino, List<String> bovinoIds) {


            Potrero potrerodestino = potreroRepository.findById(potreroDestino).orElseThrow(() ->
                new ResourceNotFoundExcepcion("Potrero no existe", "id : "
                        + potreroDestino, potreroDestino));
        Potrero potreroorig = potreroRepository.findById(potreroOrig).orElseThrow(() ->
                new ResourceNotFoundExcepcion("Potrero no existe", "id : "
                        + potreroOrig, potreroOrig));

        LocalDate fechasalida = LocalDate.now();
        Date sqlCurrentDate = Date.valueOf(fechasalida);
        movimientoBovinoRepository.updateFechaDeSalidaTraslado(sqlCurrentDate,potreroOrig,bovinoIds);

        for (String bovinoId : bovinoIds) {
            long numberConvert = Long.parseLong(bovinoId);
            Bovino bovino = bovinoRepository.findById(bovinoId).orElseThrow(() ->
                    new ResourceNotFoundExcepcion("Bovino no encontrado", "numero : " + bovinoId, numberConvert));

            // Crear nuevo movimiento bovino
            MovimientoBovino nuevoMovimiento = new MovimientoBovino();
            nuevoMovimiento.setBovino(bovino);
            nuevoMovimiento.setPotrero(potrerodestino);
            nuevoMovimiento.setFecha_de_ingreso(LocalDate.now()); // Fecha de ingreso actual
            nuevoMovimiento.setFecha_de_salida(null); // Fecha de salida aún no definida

            // Guardar el nuevo movimiento
            movimientoBovinoRepository.save(nuevoMovimiento);
        }

    }


    private void convertSaveMovimientoBovino(Potrero potrerodestino, LocalDate fechaDeIngreso, List<MovimientoBovinoDTO> movimientoBovinoDTOList) {
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



}

