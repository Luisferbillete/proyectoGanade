package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.BovinoDTO;

import java.util.List;

public interface BovinoService {
    public BovinoDTO save(long propietarioid,long potreroId, BovinoDTO bovinoDTO);
    public BovinoDTO saves(BovinoDTO bovinoDTO);
    public List<BovinoDTO> BOVINO_DTO_LIST();
    public BovinoDTO GetBovino(String number);
    public BovinoDTO UpdateBovino(BovinoDTO bovinoDTO, String number);
    public void DeleteBovino(String number);
}
