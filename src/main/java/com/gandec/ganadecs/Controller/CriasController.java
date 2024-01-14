package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.Crias.CriasDTO;
import com.gandec.ganadecs.DTO.Parto.Birthsdto;
import com.gandec.ganadecs.Services.CriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Ganadec/Crias")
public class CriasController {
    private final CriaService criaService;

    @PostMapping("/save/{numeroBovino}/{fechaNacimiento}")
    public ResponseEntity<String> saveCria(@RequestBody CriasDTO criasDTO,
                                           @PathVariable String numeroBovino,
                                           @PathVariable LocalDate fechaNacimiento)
    {

        criaService.save(criasDTO,numeroBovino,fechaNacimiento);
        return ResponseEntity.ok("Cria guardada");
    }
    @PutMapping("/update/{numeroCria}")
    public ResponseEntity<String> updateCria(@Valid @RequestBody CriasDTO criasDTO,
                                             @PathVariable String numeroCria)
    {

        criaService.update(criasDTO,numeroCria);
        return ResponseEntity.ok("Cria Actualizada");
    }

    @PutMapping("/destete/{fechadeDestete}/{numeroBovino}")
    public ResponseEntity<String> destete2(@PathVariable LocalDate fechadeDestete, @PathVariable String numeroBovino){
        criaService.destete(fechadeDestete,numeroBovino);
        return ResponseEntity.ok("Cria destetada");
    }
    @GetMapping("/obtenerCriasPorNumeroParto/{numeroBovino}")
    public ResponseEntity<?> obtenerCriasPorNumeroParto(@PathVariable String numeroBovino){
        return ResponseEntity.ok(criaService.obtenerCriasPorNumeroParto(numeroBovino));
    }
    @GetMapping("/findByNumeroCria/{numeroCria}")
    public ResponseEntity<Birthsdto> findByNumeroCria(@PathVariable String numeroCria){
        return ResponseEntity.ok(criaService.findByNumeroCria(numeroCria));
    }
}
