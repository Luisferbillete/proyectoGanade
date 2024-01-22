package com.gandec.ganadecs.Services.Impl;

import com.gandec.ganadecs.DTO.Hierro.HierroDto;
import com.gandec.ganadecs.Entity.Hierro;
import com.gandec.ganadecs.Entity.Propietario;
import com.gandec.ganadecs.Excepciones.ResourceNotFoundExcepcion;
import com.gandec.ganadecs.Repository.HierroRepository;
import com.gandec.ganadecs.Repository.PropietariosRepository;
import com.gandec.ganadecs.Services.CloudinaryService;
import com.gandec.ganadecs.Services.HierroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HierroServiceImp implements HierroService {
    private final HierroRepository hierroRepository;
    private final PropietariosRepository propietariosRepository;
    private final CloudinaryService cloudinaryService;

    @Override
    public void save( long propietaryId, MultipartFile file) throws IOException {
        BufferedImage bi = ImageIO.read(file.getInputStream());
        if (bi == null) {
            throw new IOException("Failed to read image" + file.getOriginalFilename());
        }
        Propietario propietario=propietariosRepository.findById(propietaryId)
                .orElseThrow(()->new ResourceNotFoundExcepcion
                        ("Propietario","id",propietaryId));

        Map<?,?> result = cloudinaryService.upload2(file);
        Hierro hierro=new Hierro((String) result.get("original_filename"),(String) result.get("url"),(String) result.get("public_id"),propietario) ;
        hierroRepository.save(hierro);

    }

    @Override
    public void delete(long id) throws IOException {
        Hierro hierro=hierroRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundExcepcion
                        ("Hierro","id",id));
        Map<?,?> result = cloudinaryService.delete(hierro.getHierroId());
        hierroRepository.delete(hierro);
    }

    @Override
    public List<HierroDto> findAllHierros() {
        return hierroRepository.findAllHierros();
    }

    @Override
    public List<Hierro> findHierrosByPropietario(long propietarioId) {
        return hierroRepository.findHierrosByPropietario(propietarioId);
    }


}
