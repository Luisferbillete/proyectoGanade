package com.gandec.ganadecs.Services.Impl;

import com.gandec.ganadecs.DTO.BovinoDTO;

import com.gandec.ganadecs.DTO.Bovinos.BovinosDTO;
import com.gandec.ganadecs.DTO.Bovinos.CreateBovino;
import com.gandec.ganadecs.DTO.EntityDTO;
import com.gandec.ganadecs.Entity.Bovino;
import com.gandec.ganadecs.Entity.MovimientoBovino;
import com.gandec.ganadecs.Entity.Potrero;
import com.gandec.ganadecs.Entity.Propietario;
import com.gandec.ganadecs.Excepciones.ResourceNotFoundExcepcion;
import com.gandec.ganadecs.Mapeador.Mappers;
import com.gandec.ganadecs.Mapeador.Util.CalculadoraEdadUtil;
import com.gandec.ganadecs.Repository.BovinoRepository;
import com.gandec.ganadecs.Repository.MovimientoBovinoRepository;
import com.gandec.ganadecs.Repository.PotreroRepository;
import com.gandec.ganadecs.Repository.PropietariosRepository;
import com.gandec.ganadecs.Services.BovinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BovinoserviceImpl implements BovinoService {
   private final BovinoRepository bovinoRepository;

    private final PropietariosRepository propietariosRepository;
    private final Mappers mappers;
    private final PotreroRepository potreroRepository;
    private final MovimientoBovinoRepository movimientoBovinoRepository;
    private final CalculadoraEdadUtil calculadoraEdadUtil;


    @Override
    public String savess(CreateBovino createBovino) {
       Propietario propietario=propietariosRepository.findById(createBovino.getPropietario()).orElseThrow(()->
               new ResourceNotFoundExcepcion("Propietario","id",createBovino.getPropietario()));
        String numeroId=createBovino.getNumero();
        Optional<Bovino> existebovino=bovinoRepository.findById(numeroId);
        if(existebovino.isPresent()) {
            throw new IllegalStateException("El bovino con numero " + numeroId + " ya existe en la base de datos.");
        }
        Bovino bovino = new Bovino();
        bovino = (Bovino) mappers.convertToEntity((EntityDTO) createBovino, bovino);

        bovino.setPropietario(propietario);
        bovino = bovinoRepository.save(bovino);
        Potrero potrero = potreroRepository.findById(createBovino.getPotrero()).orElseThrow(() ->
                new ResourceNotFoundExcepcion("Potrero", "id", createBovino.getPotrero()));
        MovimientoBovino movimientoBovino = new MovimientoBovino();
        movimientoBovino.setBovino(bovino);
        movimientoBovino.setPotrero(potrero);
        movimientoBovino.setFecha_de_ingreso(createBovino.getFecha_de_ingreso());
        movimientoBovinoRepository.save(movimientoBovino);
        return "Bovino guardado con exito";
    }

    @Override
    public String save(long propietarioid,long potreroId,BovinoDTO bovinoDTO) {
        Propietario propietario=propietariosRepository.findById(propietarioid).orElseThrow(()->
                new ResourceNotFoundExcepcion
                        ("Propietario","id",propietarioid));
        String numeroId=bovinoDTO.getNumero();
        Optional<Bovino> existebovino=bovinoRepository.findById(numeroId);
        if(existebovino.isPresent()){
            throw new IllegalStateException("El bovino con numero " + numeroId + " ya existe en la base de datos.");


        }else {

            Bovino bovino = new Bovino();

            bovino = (Bovino) mappers.convertToEntity(bovinoDTO, bovino);
            ValidarNegocio(bovinoDTO);

            bovino.setPropietario(propietario);

            bovino = bovinoRepository.save(bovino);
            Potrero potrero = potreroRepository.findById(potreroId).orElseThrow(() ->
                    new ResourceNotFoundExcepcion("Potrero", "id", potreroId));
            MovimientoBovino movimientoBovino = new MovimientoBovino();
            movimientoBovino.setBovino(bovino);
            movimientoBovino.setPotrero(potrero);
            movimientoBovino.setFecha_de_ingreso(bovinoDTO.getFecha_de_ingreso());
            movimientoBovinoRepository.save(movimientoBovino);

        }
        return "Bovino guardado con exito";
    }

    private void ValidarNegocio(BovinoDTO bovinoDTO) {
        if (Objects.equals(bovinoDTO.getNegocio(), "Kilos")) {
            if (bovinoDTO.getKilos() == 0) {
                throw new IllegalStateException("los kilos no pueden ser cero");
            }
            if (bovinoDTO.getKilos() < 0) {
                throw new IllegalStateException("los kilos no pueden ser negativos");
            }
        }
        if (Objects.equals(bovinoDTO.getNegocio(), "Abaluo")) {
            if (bovinoDTO.getAbaluo() == 0) {
                throw new IllegalStateException("El abaluo no puede ser cero");
            }
            if (bovinoDTO.getAbaluo() < 0) {
                throw new IllegalStateException("El abaluo no puede ser negativo");
            }
        }
        if (Objects.equals(bovinoDTO.getNegocio(), "Kilos y Precio")) {
            if (bovinoDTO.getKilos() == 0) {
                throw new IllegalStateException("los kilos no pueden ser cero");
            }
            if (bovinoDTO.getKilos() < 0) {
                throw new IllegalStateException("los kilos no pueden ser negativos");
            }
            if (bovinoDTO.getPreciokilo() == 0) {
                throw new IllegalStateException("El precio no pueden estar en cero");
            }
            if (bovinoDTO.getPreciokilo() < 0) {
                throw new IllegalStateException("El precio no pueden ser negativos");
            }
        }
    }

    @Override
    public String update(BovinoDTO bovinoDTO, String NumerBovino) {
       long numero=Long.parseLong(NumerBovino);
        Bovino bovino=bovinoRepository.findById(NumerBovino).orElseThrow(()->
                new ResourceNotFoundExcepcion("Bovino","numero",numero));
        bovino= (Bovino) mappers.convertToEntity(bovinoDTO,bovino);

        Propietario propietario=propietariosRepository.findById(bovinoDTO.getPropietario()).orElseThrow(()->
                new ResourceNotFoundExcepcion("Propietario","id",bovinoDTO.getPropietario()));

        Bovino bovinos;
        bovinos = (Bovino) mappers.convertToEntity(bovinoDTO, bovino);
        bovinos.setNumero(NumerBovino);
        bovinos.setPropietario(propietario);

        ValidarNegocio(bovinoDTO);
        bovinoRepository.save(bovinos);
        return "Bovino actualizado con exito";
    }

    @Override
    public List<BovinosDTO> BovinesGetAll() {
        List<BovinosDTO> bovinosConCategoria = new ArrayList<>();
        List<BovinosDTO> todosBovinos = bovinoRepository.BovinesGetAll();
        bovinesCategory(bovinosConCategoria, todosBovinos);
        return bovinosConCategoria;

    }



    @Override
    public List<BovinosDTO> BovinesGetallByPropietary(long propietarioId) {

        List<BovinosDTO> bovinosConCategoria = new ArrayList<>();
        List<BovinosDTO> todosBovinos = bovinoRepository.BovinesGetallByPropietary(propietarioId);
        bovinesCategory(bovinosConCategoria, todosBovinos);
        return bovinosConCategoria;   }

    @Override
    public List<BovinosDTO> BovinesGetallByPropietaryAndSexo(long propietarioId, String sexo) {
        List<BovinosDTO> bovinosConCategoria = new ArrayList<>();
        List<BovinosDTO> todosBovinos = bovinoRepository.BovinesGetallByPropietaryAndSexo(propietarioId, sexo);
        bovinesCategory(bovinosConCategoria, todosBovinos);
        return bovinosConCategoria;
    }

    @Override
    public List<BovinosDTO> BovinesGetallBySexo(String sexo) {
        List<BovinosDTO> bovinosConCategoria = new ArrayList<>();
        List<BovinosDTO> todosBovinos = bovinoRepository.BovinesGetallBySexo(sexo);
        bovinesCategory(bovinosConCategoria, todosBovinos);
        return bovinosConCategoria;
    }

    @Override
    public Optional<BovinosDTO> BovinesGetallByNumero(String numero) {
        List<BovinosDTO> bovinosConCategoria = new ArrayList<>();
        List<BovinosDTO> todosBovinos = bovinoRepository.BovinesGetallByNumero(numero);
        bovinesCategory(bovinosConCategoria, todosBovinos);
        return bovinosConCategoria.stream().findFirst();

    }



    private void bovinesCategory(List<BovinosDTO> bovinosConCategoria, List<BovinosDTO> todosBovinos) {
        for (BovinosDTO bovDTO : todosBovinos) {
            String categoria = calculadoraEdadUtil.calcularCategoria(bovDTO.getFecha_de_nacimiento(), bovDTO.getSexo());
            bovDTO.setCategoria(categoria);
            bovinosConCategoria.add(bovDTO);
        }
    }


}
