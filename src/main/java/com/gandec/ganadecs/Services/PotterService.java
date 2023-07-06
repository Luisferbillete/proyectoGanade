package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.PotreroComboDto;
import com.gandec.ganadecs.DTO.PotreroDto;

import java.util.List;

public interface PotterService {
    public PotreroDto Saves(PotreroDto potreroDto);
    public List<PotreroDto> POTRERO_DTO_LIST();
    public List<PotreroComboDto> POTRERO_COMBO_DTO_LIST();
    public PotreroDto UpdatePotter(PotreroDto potreroDto, long id);
    public PotreroDto GetPotter(long id);

    public void DeletePotrero(long id);

}
