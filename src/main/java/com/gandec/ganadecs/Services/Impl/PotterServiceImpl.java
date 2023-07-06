package com.gandec.ganadecs.Services.Impl;

import com.gandec.ganadecs.DTO.PotreroComboDto;
import com.gandec.ganadecs.DTO.PotreroDto;
import com.gandec.ganadecs.Entity.Potrero;
import com.gandec.ganadecs.Excepciones.ResourceNotFoundExcepcion;
import com.gandec.ganadecs.Mapeador.Mappers;
import com.gandec.ganadecs.Repository.PotreroRepository;
import com.gandec.ganadecs.Services.PotterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gandec.ganadecs.Mapeador.MapperList.mapList;

@Service
public class PotterServiceImpl implements PotterService {
    @Autowired
    private PotreroRepository potreroRepository;
    @Autowired
    private Mappers mappers;


    @Override
    public PotreroDto Saves(PotreroDto potreroDto) {
        Potrero potrero=new Potrero();
        potrero= (Potrero) mappers.convertToEntity(potreroDto, potrero);
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
