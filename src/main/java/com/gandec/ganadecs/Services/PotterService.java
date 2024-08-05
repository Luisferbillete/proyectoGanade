package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.PotreroComboDto;
import com.gandec.ganadecs.DTO.PotreroDto;
import org.springframework.data.domain.Page;


import java.util.List;

public interface PotterService {
     PotreroDto Saves(PotreroDto potreroDto);
     List<PotreroDto> POTRERO_DTO_LIST();

     Page<PotreroDto> POTRERO_LIST_PAGE2(Integer start, Integer limit);
     List<PotreroComboDto> POTRERO_COMBO_DTO_LIST();
     PotreroDto UpdatePotter(PotreroDto potreroDto, long id);
     PotreroDto GetPotter(long id);

     void DeletePotrero(long id);

}
