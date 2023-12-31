package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.PartoDTO;
import com.gandec.ganadecs.DTO.PartosDTO;
import com.gandec.ganadecs.Services.PartosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Ganadec/Partos")
public class PartoController {
    private final PartosService partosService;
    //private final Mappers mappers;
   /*@PostMapping("/save/{BovinoId}")
    public ResponseEntity<PartoDTO> save(@RequestBody PartoDTO partoDTO, @PathVariable String BovinoId){
        return new ResponseEntity<>(partosService.save(partoDTO,BovinoId), HttpStatus.CREATED);
    }
    @PostMapping("/saves/{bovino}")
    public Parto saves(@RequestBody Parto parto, @PathVariable Bovino bovino){
        partosService.saves(parto,bovino);
        return parto;
    }*/
    @PostMapping("/savess/{numeroId}")
    public ResponseEntity<String>savess(@RequestBody PartoDTO partoDTO,@PathVariable String numeroId){
        partosService.savess(partoDTO,numeroId);
        return ResponseEntity.ok("Registro guardado con exito.");

    }
    @GetMapping("/get/{numeroId}")
    public  ResponseEntity<List<PartosDTO>> getPartoBovino(@PathVariable String numeroId){
        List<PartosDTO> partosDTOList=partosService.getPartoBovino(numeroId);
        return new ResponseEntity<>(partosDTOList, HttpStatus.OK);
    }
}
