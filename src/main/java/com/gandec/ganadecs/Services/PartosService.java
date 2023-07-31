package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.PartoDTO;
import com.gandec.ganadecs.DTO.PartosDTO;

import java.util.List;

public interface PartosService {

   /* public PartoDTO save(PartoDTO partoDTO,String bovino);
    //public  Parto saves(Parto parto, Bovino bovino);
    /***************************************************************************************/
    public void savess(PartoDTO partoDTO,String bovinoId);
    public List<PartosDTO> getPartoBovino(String numeroId);

}
