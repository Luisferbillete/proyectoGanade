package com.gandec.ganadecs.Services.Impl;

import com.gandec.ganadecs.DTO.FotosBovinos.FotosBovinosDto;
import com.gandec.ganadecs.Entity.Bovino;
import com.gandec.ganadecs.Entity.FotoBovinos;
import com.gandec.ganadecs.Excepciones.ResourceNotFoundExcepcion;
import com.gandec.ganadecs.Repository.BovinoRepository;
import com.gandec.ganadecs.Repository.FotoBovinosRepository;
import com.gandec.ganadecs.Services.CloudinaryService;
import com.gandec.ganadecs.Services.FotoBovinoService;
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

public class FotoBovinoServiceImpl implements FotoBovinoService {
    private final BovinoRepository bovinoRepository;
    private final CloudinaryService cloudinaryService;
    private final FotoBovinosRepository fotoBovinosRepository;
    @Override
    public void saveFotoBovino(MultipartFile file, String idBovino) throws IOException {
        BufferedImage bi = ImageIO.read(file.getInputStream());
        if (bi == null) {
            throw new IOException("Failed to read image" + file.getOriginalFilename());
        }
        long numero=Long.parseLong(idBovino);
        Bovino bovino=bovinoRepository.findById(idBovino).orElseThrow(()->
                new ResourceNotFoundExcepcion("Bovino","numero",numero));

        Map<?,?> result = cloudinaryService.upload2(file);
        FotoBovinos fotoBovinos=new FotoBovinos((String) result.get("original_filename"),(String) result.get("url"),(String) result.get("public_id"),bovino) ;
        fotoBovinosRepository.save(fotoBovinos);
    }

    @Override
    public void deleteFotoBovino(long id) throws IOException {
        FotoBovinos fotoBovinos=fotoBovinosRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundExcepcion("FotoBovino","id",id));
        Map<?,?> result = cloudinaryService.delete((fotoBovinos.getIdFoto()));
        fotoBovinosRepository.delete(fotoBovinos);
    }

    @Override
    public List<FotosBovinosDto> findAllFotosBovinos() {
        return fotoBovinosRepository.findAllFotosBovinos();
    }

    @Override
    public List<FotosBovinosDto> findFotosBovinosByNumeroBovino(String numeroBovino) {
        return fotoBovinosRepository.findFotosBovinosByNumeroBovino(numeroBovino);
    }
}
