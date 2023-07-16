package com.gandec.ganadecs.Services.Impl;

import com.gandec.ganadecs.DTO.BovinoDTO;
import com.gandec.ganadecs.Entity.Bovino;
import com.gandec.ganadecs.Entity.MovimientoBovino;
import com.gandec.ganadecs.Entity.Potrero;
import com.gandec.ganadecs.Entity.Propietario;
import com.gandec.ganadecs.Excepciones.ResourceNotFoundExcepcion;
import com.gandec.ganadecs.Mapeador.Mappers;
import com.gandec.ganadecs.Repository.BovinoRepository;
import com.gandec.ganadecs.Repository.MovimientoBovinoRepository;
import com.gandec.ganadecs.Repository.PotreroRepository;
import com.gandec.ganadecs.Repository.PropietariosRepository;
import com.gandec.ganadecs.Services.BovinoService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gandec.ganadecs.Mapeador.MapperList.mapList;

@Service
public class BovinoserviceImpl implements BovinoService {
   private final BovinoRepository bovinoRepository;

    private final PropietariosRepository propietariosRepository;
    private final Mappers mappers;
    private final PotreroRepository potreroRepository;
    private final MovimientoBovinoRepository movimientoBovinoRepository;

    public BovinoserviceImpl(BovinoRepository bovinoRepository,
                             PropietariosRepository propietariosRepository, Mappers mappers, PotreroRepository potreroRepository, MovimientoBovinoRepository movimientoBovinoRepository) {
        this.bovinoRepository = bovinoRepository;
        this.propietariosRepository = propietariosRepository;
        this.mappers = mappers;
        this.potreroRepository = potreroRepository;
        this.movimientoBovinoRepository = movimientoBovinoRepository;
    }

    @Override
    public BovinoDTO save(long propietarioid,long potreroId,BovinoDTO bovinoDTO) {
        Propietario propietario=propietariosRepository.findById(propietarioid).orElseThrow(()->
                new ResourceNotFoundExcepcion
                        ("Propietario","id",propietarioid));

               Bovino bovino=new Bovino();

        bovino= (Bovino) mappers.convertToEntity(bovinoDTO,bovino);


        bovino.setPropietario(propietario);

        bovino=bovinoRepository.save(bovino);
        Potrero potrero=potreroRepository.findById(potreroId).orElseThrow(()->
                new ResourceNotFoundExcepcion("Potrero","id",potreroId));
        MovimientoBovino movimientoBovino=new MovimientoBovino();
        movimientoBovino.setBovino(bovino);
        movimientoBovino.setPotrero(potrero);
        movimientoBovino.setFecha_de_ingreso(bovinoDTO.getFecha_de_ingreso());
        movimientoBovino=movimientoBovinoRepository.save(movimientoBovino);

        bovinoDTO= (BovinoDTO) mappers.convertToDto(bovino,bovinoDTO);
        return bovinoDTO;
    }

    @Override
    public BovinoDTO saves(BovinoDTO bovinoDTO) {
       // BovinoDTO bovinoDTO1=new BovinoDTO();
        Bovino bovino=new Bovino();
        bovino=(Bovino)mappers.convertToEntity(bovinoDTO,bovino);
        //bovino.setPropietario(bovinoDTO.getPropietario());
        bovino=bovinoRepository.save(bovino);

        bovinoDTO= (BovinoDTO) mappers.convertToDto(bovino,bovinoDTO);
        return bovinoDTO;
    }


    @Override
    public List<BovinoDTO> BOVINO_DTO_LIST() {
        List<Bovino> bovinoList=bovinoRepository.findAll();
        return mapList(bovinoList,BovinoDTO.class);
    }

    @Override
    public BovinoDTO GetBovino(String number) {
        BovinoDTO bovinoDTO=new BovinoDTO();
        long numberConvert=Long.parseLong(number);
        Bovino bovino=bovinoRepository.findById(number).orElseThrow(()->
                new ResourceNotFoundExcepcion("Bovino","numero",numberConvert));
        return (BovinoDTO) mappers.convertToDto(bovino,bovinoDTO);
    }

    @Override
    public BovinoDTO UpdateBovino(BovinoDTO bovinoDTO, String number) {
        return null;
    }

    @Override
    public void DeleteBovino(String number) {

    }
}
