package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.FotosBovinos.FotosBovinosDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FotoBovinoService {
    void saveFotoBovino(MultipartFile file, String idBovino) throws IOException;
    void deleteFotoBovino(long id) throws IOException;
    List<FotosBovinosDto> findAllFotosBovinos();
    List<FotosBovinosDto> findFotosBovinosByNumeroBovino(String numeroBovino);


}
