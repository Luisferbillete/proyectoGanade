package com.gandec.ganadecs.Services.Impl;

import com.gandec.ganadecs.DTO.PotreroComboDto;
import com.gandec.ganadecs.DTO.PotreroDto;
import com.gandec.ganadecs.Entity.Potrero;
import com.gandec.ganadecs.Excepciones.ResourceNotFoundExcepcion;
import com.gandec.ganadecs.Mapeador.Mappers;
import com.gandec.ganadecs.Repository.PotreroRepository;
import com.gandec.ganadecs.Services.PotterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;

import static com.gandec.ganadecs.Mapeador.MapperList.mapList;

@Service
public class PotterServiceImpl implements PotterService {
    private final PotreroRepository potreroRepository;

    private final Mappers mappers;

    public PotterServiceImpl(PotreroRepository potreroRepository, Mappers mappers) {
        this.potreroRepository = potreroRepository;
        this.mappers = mappers;
    }


    @Override
    public PotreroDto Saves(PotreroDto potreroDto) {
        Potrero potrero=new Potrero();
        potrero= (Potrero) mappers.convertToEntity(potreroDto, potrero);
        potrero.setHectareas(potreroDto.getHectareas());
        potrero=potreroRepository.save(potrero);
        potreroDto= (PotreroDto) mappers.convertToDto(potrero,potreroDto);
        return potreroDto;
    }

    @Override
    public List<PotreroDto> POTRERO_DTO_LIST() {
        List<Potrero> potreroList=potreroRepository.findAll();
        return mapList(potreroList,PotreroDto.class);
    }





    @Override
    public Page<PotreroDto> POTRERO_LIST_PAGE2(Integer start, Integer limit) {
        Pageable pageable = PageRequest.of(start, limit);
        Page<Potrero> potreroPage = potreroRepository.findAll(pageable);
        if (potreroPage != null) {
            return potreroPage.map(potrero -> (PotreroDto) mappers.convertToDto(potrero, new PotreroDto()));
        }
        return null;
    }

    @Override
    public List<PotreroComboDto> POTRERO_COMBO_DTO_LIST() {
        List<Potrero> potreroList=potreroRepository.findAll();
        return mapList(potreroList, PotreroComboDto.class);
    }

    @Override
    public PotreroDto UpdatePotter(PotreroDto potreroDto, long id) {
        Potrero potrero=potreroRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundExcepcion("Potrero","id",id));
        potrero.setNombre(potreroDto.getNombre());
        potrero.setHectareas(potreroDto.getHectareas());
        Potrero potterActualize=potreroRepository.save(potrero);
        return (PotreroDto) mappers.convertToDto(potterActualize,potreroDto);
    }

    @Override
    public PotreroDto GetPotter(long id) {
        PotreroDto potreroDto=new PotreroDto();
        Potrero potrero=potreroRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundExcepcion("Potrero","id",id));
        return (PotreroDto) mappers.convertToDto(potrero,potreroDto);
    }

    @Override
    public void DeletePotrero(long id) {
        Potrero potrero=potreroRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundExcepcion("Potrero","id",id));
        potreroRepository.delete(potrero);

    }
}
