package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.Services.FotoBovinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Ganadec/fotosbovino")
public class FotosBovinoController {
    private final FotoBovinoService fotoBovinoService;
    @PostMapping("/save/{idBovino}")
    public ResponseEntity<String> saveFotoBovino(@PathVariable(name = "idBovino") String idBovino,
                                                 @RequestPart("file") MultipartFile file) throws IOException {
        fotoBovinoService.saveFotoBovino(file, idBovino);
        return new ResponseEntity<>("Archive subside excitement ! ", HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFotoBovino(@PathVariable(name = "id") long id) throws IOException {
        fotoBovinoService.deleteFotoBovino(id);
        return new ResponseEntity<>("Archive delete excitement ! ", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllFotosBovinos(){
        return new ResponseEntity<>(fotoBovinoService.findAllFotosBovinos(),HttpStatus.OK);
    }
    @GetMapping("/all/{numeroBovino}")
    public ResponseEntity<?> findFotosBovinosByNumeroBovino(@PathVariable(name = "numeroBovino") String numeroBovino){
        return new ResponseEntity<>(fotoBovinoService.findFotosBovinosByNumeroBovino(numeroBovino),HttpStatus.OK);
    }

}
