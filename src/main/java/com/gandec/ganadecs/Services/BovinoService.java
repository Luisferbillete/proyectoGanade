package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.BovinoDTO;

import java.util.List;

public interface BovinoService {
    String save(long propietarioid,long potreroId, BovinoDTO bovinoDTO);

    List<BovinoDTO> BOVINO_DTO_LIST();
    BovinoDTO GetBovino(String number);
     BovinoDTO UpdateBovino(BovinoDTO bovinoDTO, String number);
     void DeleteBovino(String number);
}
