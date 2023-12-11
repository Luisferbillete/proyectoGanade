package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.PartoDTO;
import com.gandec.ganadecs.DTO.PartosDTO;
import com.gandec.ganadecs.Services.PartosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Ganadec/Partos")
public class PartoController {
    private final PartosService partosService;

    @PostMapping("/savess/{numeroId}")
    public ResponseEntity<String> savess(@Valid @RequestBody PartoDTO partoDTO, @PathVariable String numeroId) {
        partosService.savess(partoDTO, numeroId);
        return ResponseEntity.ok("Registro guardado con exito.");

    }

    @GetMapping("/get/{numeroId}")
    public ResponseEntity<List<PartosDTO>> getPartoBovino(@PathVariable String numeroId) {
        List<PartosDTO> partosDTOList = partosService.getPartoBovino(numeroId);
        return new ResponseEntity<>(partosDTOList, HttpStatus.OK);
    }

    @PostMapping("/actualizarFechaDestete/{numeroId}/{fechaDestete}")
    public ResponseEntity<String> destete(@PathVariable String numeroId,
                                          @PathVariable LocalDate fechaDestete) {
        int count = partosService.actualizarFechaDestete(numeroId, fechaDestete);
        if (count == 0) {
            throw new IllegalStateException("El bovino con numero " + numeroId +
                    " no se encuentra registrado como parida.");
        }
        return ResponseEntity.ok("destete  exitoso.");

    }
    @PutMapping("/actualizar/{numero}")
    public ResponseEntity<String> ActualizarNombreParto(@Valid @PathVariable String numero, @RequestBody PartoDTO partoDTO){
        partosService.actualizarNombreParto(numero,partoDTO.getNombre());
        return ResponseEntity.ok("Nombre actualizado con exito.");
    }

}
