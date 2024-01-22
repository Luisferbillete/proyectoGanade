package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.Hierro.HierroDto;
import com.gandec.ganadecs.Entity.Hierro;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface HierroService {
    void save(long propietaryId, MultipartFile file) throws IOException;
    void delete(long id) throws IOException;
    List<HierroDto> findAllHierros();
    List<Hierro> findHierrosByPropietario(long propietarioId);

}
