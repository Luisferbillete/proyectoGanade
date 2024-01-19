package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.BovinoDTO;
import com.gandec.ganadecs.DTO.Bovinos.BovinosDTO;
import com.gandec.ganadecs.Services.BovinoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/Ganadec/Bovinos")
public class BovinoController {
    private final BovinoService bovinoService;


    @GetMapping("/all")
    public ResponseEntity<List<BovinosDTO>> getAll() {
        return new ResponseEntity<>(bovinoService.BovinesGetAll(), HttpStatus.OK);
    }
    @GetMapping("/all/{propietarioId}")
    public ResponseEntity<List<BovinosDTO>> getAllByPropietary(@PathVariable long propietarioId) {
        return new ResponseEntity<>(bovinoService.BovinesGetallByPropietary(propietarioId), HttpStatus.OK);
    }
    @GetMapping("/all/{propietarioId}/{sexo}")
    public ResponseEntity<List<BovinosDTO>> getAllByPropietaryAndSexo(@PathVariable long propietarioId,
                                                                      @PathVariable String sexo) {
        return new ResponseEntity<>(bovinoService.BovinesGetallByPropietaryAndSexo(propietarioId, sexo), HttpStatus.OK);
    }
    @GetMapping("/allsex/{sexo}")
    public ResponseEntity<List<BovinosDTO>> getAllBySexo(@PathVariable String sexo) {
        return new ResponseEntity<>(bovinoService.BovinesGetallBySexo(sexo), HttpStatus.OK);
    }
    @GetMapping("/allnum/{numero}")
   public ResponseEntity<Optional<BovinosDTO>> getAllByNumero(@PathVariable String numero) {
        return new ResponseEntity<>(bovinoService.BovinesGetallByNumero(numero), HttpStatus.OK);
    }
    @PostMapping("/save/{propietarioid}/{potreroId}")
    public ResponseEntity<String> SaveBovino(@PathVariable long propietarioid, @PathVariable long potreroId,
                                             @Valid @RequestBody BovinoDTO bovinoDTO) {
        return new ResponseEntity<>(bovinoService.save(propietarioid, potreroId, bovinoDTO), HttpStatus.CREATED);

    }


    @PutMapping("/update/{numero}")
    public ResponseEntity<String> update(@PathVariable(name = "numero") String numero,
                                         @Valid @RequestBody BovinoDTO bovinoDTO) {
        return new ResponseEntity<>(bovinoService.update(bovinoDTO, numero), HttpStatus.CREATED);
    }





}
