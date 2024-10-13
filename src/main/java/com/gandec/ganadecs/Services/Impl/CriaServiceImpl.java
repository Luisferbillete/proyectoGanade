package com.gandec.ganadecs.Services.Impl;

import com.gandec.ganadecs.DTO.Crias.Cdto;
import com.gandec.ganadecs.DTO.Crias.CrearCrias;
import com.gandec.ganadecs.DTO.Crias.CriasDTO;
import com.gandec.ganadecs.DTO.Crias.CriasPorParto;
import com.gandec.ganadecs.DTO.Parto.Birthsdto;
import com.gandec.ganadecs.Entity.Crias;
import com.gandec.ganadecs.Entity.Partos;
import com.gandec.ganadecs.Excepciones.AlreadyDestetadaException;
import com.gandec.ganadecs.Excepciones.InvalidDesteteDateException;
import com.gandec.ganadecs.Excepciones.NoCriaFoundException;
import com.gandec.ganadecs.Mapeador.Mappers;
import com.gandec.ganadecs.Repository.CriasRepository;
import com.gandec.ganadecs.Repository.PartoRepository;
import com.gandec.ganadecs.Services.CriaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.gandec.ganadecs.Mapeador.MapperList.mapList;

@Service
@RequiredArgsConstructor
public class CriaServiceImpl implements CriaService {
    private final CriasRepository criasRepository;
    private final PartoRepository partoRepository;
    private final Mappers mappers;

    @Transactional
    @Override
    public void save(CrearCrias crearCrias) {
        Partos partos = partoRepository.findById(crearCrias.getBovino()).orElseThrow(() ->
                new RuntimeException("Parto no Registrado en la base de datos"));
        List<Cdto> criasList = criasRepository.verificarFechaNacimientoIgual(crearCrias.getBovino());
        if (!criasList.isEmpty()) {
            for (Cdto crias : criasList) {
                int con=crias.getFecha_nacimiento().compareTo(crearCrias.getFecha_nacimiento());
                if (con!=0)
                    throw new RuntimeException("El vaca  numero : " + crearCrias.getBovino() +
                            "  se encuentra parida ");
            }
        }
        int numeroCrias = criasRepository.contarCriasPorNumeroParto(crearCrias.getBovino())+1;
        Crias crias = new Crias();
        crias = (Crias) mappers.convertToEntity(crearCrias, crias);
        crias.setNumero(crearCrias.getBovino()+"-"+numeroCrias);
        crias.setFecha_nacimiento(crearCrias.getFecha_nacimiento());
        crias.setEstado("vivo");
        Set<Partos> partoscrias = new HashSet<>();
        partoscrias.add(partos);
        crias.setPartos(partoscrias);
        criasRepository.save(crias);
    }


    @Override
    public void update(CriasDTO criasDTO, String numeroCria) {
        Crias crias = criasRepository.findById(numeroCria).orElseThrow(() ->
                new RuntimeException("Cria no Registrada en la base de datos"));
        crias = (Crias) mappers.convertToEntity(criasDTO, crias);
        crias.setNumero(numeroCria);
        criasRepository.save(crias);
    }

    @Override
    public void destete(LocalDate fechadeDestete, String numeroBovino) {
        List<Cdto> criasList = criasRepository.obtenerCriasPorNumeroPartodto(numeroBovino);
        if (criasList.isEmpty())
            throw new NoCriaFoundException("La vaca  numero : " + numeroBovino +
                    " no tiene crias ");
        for (Cdto crias : criasList) {
            if (crias.getFecha_destete() != null)
                throw new AlreadyDestetadaException("El vaca  numero : " + numeroBovino +
                        " se encuentra destetada ");
        }
        List<Crias> listcrias=mapList(criasList,Crias.class);
        for (Crias crias : listcrias) {
            int con=crias.getFecha_nacimiento().compareTo(fechadeDestete);
            if (con>0)
                throw new InvalidDesteteDateException("la vaca  numero : " + numeroBovino +
                        " no puede ser destetada antes de su fecha de nacimiento ");
            crias.setFecha_destete(fechadeDestete);
            criasRepository.save(crias);
        }
    }

    @Override
    public List<CriasPorParto> obtenerCriasPorNumeroParto(String numeroBovino) {
        List<CriasPorParto> criasPorPartos = criasRepository.obtenerCriasPorNumeroParto(numeroBovino);
        if (criasPorPartos.isEmpty())
            throw new RuntimeException("El Bovino  numero : " + numeroBovino +
                    " no tiene crias ");
        return criasPorPartos;
    }

    @Override
    public Birthsdto findByNumeroCria(String numeroCria) {
        Birthsdto birthsdto = criasRepository.findByNumeroCria(numeroCria);
        if (birthsdto == null)
            throw new RuntimeException("La cria  numero : " + numeroCria +
                    " no existe ");
        return birthsdto;
    }


}
