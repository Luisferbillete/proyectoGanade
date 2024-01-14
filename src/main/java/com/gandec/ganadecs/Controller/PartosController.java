package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.Bovinos.BovinosDTO;
import com.gandec.ganadecs.DTO.Parto.Birthsdto;
import com.gandec.ganadecs.DTO.Parto.PartosFindPropietarioDTO;
import com.gandec.ganadecs.DTO.Parto.PartosPorPropietarioYCriasYSexo;
import com.gandec.ganadecs.DTO.Parto.PartosPropietariosDTO;

import com.gandec.ganadecs.Services.PartoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Ganadec/Partos")
public class PartosController {
    private final PartoService partoService;


    @PostMapping("/save/{bovinoId}")
    public ResponseEntity<String> savePartos(@Valid @RequestBody Birthsdto birthsDTO,
                                             @PathVariable String bovinoId) {

        partoService.save(birthsDTO,bovinoId);
        return ResponseEntity.ok("Parto guardado");
    }

    @GetMapping("/findPartosAndPropietarioWithNullDestete")
    public ResponseEntity<List<PartosPropietariosDTO>> findPartosAndPropietarioWithNullDestete(){
        return ResponseEntity.ok(partoService.findPartosAndPropietarioWithNullDestete());


    }
    @GetMapping("/findPartosAndPropietarioDTOWithNullDesteteAndPropietarioId/{propietarioId}")
    public ResponseEntity<List<PartosFindPropietarioDTO>> findPartosAndPropietarioDTOWithNullDesteteAndPropietarioId
            (@PathVariable Long propietarioId){
        return ResponseEntity.ok(partoService.findPartosAndPropietarioDTOWithNullDesteteAndPropietarioId(propietarioId));

    }

    @GetMapping("/FinAllPartosPorSexoCria/{sexoCria}")
    public ResponseEntity<List<PartosPorPropietarioYCriasYSexo>> PartosPorPropietarioYCriasYSexo(@PathVariable String sexoCria){
        return ResponseEntity.ok(partoService.FinAllPartosPorSexoCria(sexoCria));

    }
    @GetMapping("/FinAllPartosPorSexoCriaYPropietary/{sexoCria}/{propietarioId}")
    public ResponseEntity<List<PartosPorPropietarioYCriasYSexo>> FinAllPartosPorSexoCriaYPropietary(@PathVariable String sexoCria,
                                                                                                    @PathVariable Long propietarioId ){
        return ResponseEntity.ok(partoService.FinAllPartosPorSexoCriaYPropietary(sexoCria,propietarioId));

    }
    @PutMapping("/ActulizarParto/{bovinoId}")
    public ResponseEntity<String> ActulizarParto(@Valid @RequestBody Birthsdto birthsDTO,
                                                 @PathVariable String bovinoId) {

        partoService.ActulizarParto(birthsDTO,bovinoId);
        return ResponseEntity.ok("Parto Actualizado");
    }





}
