package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.BovinoDTO;
import com.gandec.ganadecs.DTO.Bovinos.BovinosDTO;
import com.gandec.ganadecs.DTO.Bovinos.BovinosFindByNumero;
import com.gandec.ganadecs.DTO.Bovinos.BovinosGetAll;
import com.gandec.ganadecs.DTO.Bovinos.CreateBovino;
import com.gandec.ganadecs.Services.BovinoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/Ganadec/Bovinos")
public class BovinoController {
    private final BovinoService bovinoService;

 @PreAuthorize("hasRole('PROPIETARIO') or hasRole('ADMIN')")
 @GetMapping("/all2")
 public Page<BovinosGetAll> BovinosGetAll(@RequestParam(name = "start") Integer start,
                                   @RequestParam(name = "limit") Integer limit) {
     return bovinoService.BovinosGetAll(start, limit);
 }
    @GetMapping("/all")
    public Page<BovinosDTO> getAll( @RequestParam(name = "start") Integer start,
                                                    @RequestParam(name = "limit") Integer limit) {
     return bovinoService.BovinesGetAll(start, limit);
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
    @GetMapping("/busqueda/{numero}")
    public ResponseEntity<BovinosFindByNumero> getBovinoByNumero(@PathVariable String numero) {
        Optional<BovinosFindByNumero> bovino = bovinoService.bovinosFindByNumero(numero);
        return bovino.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<String> SaveBovino(@Valid @RequestBody CreateBovino createBovino) {
        return new ResponseEntity<>(bovinoService.saves(createBovino), HttpStatus.CREATED);
    }


    @PutMapping("/update/{numero}")
    public ResponseEntity<String> update(@PathVariable(name = "numero") String numero,
                                         @Valid @RequestBody BovinoDTO bovinoDTO) {
        return new ResponseEntity<>(bovinoService.update(bovinoDTO, numero), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{numero}")
      public ResponseEntity<String> delete(@PathVariable(name = "numero") String numero) {

        try {
            bovinoService.DeleteBovino(numero);
            return ResponseEntity.ok("Bovino eliminado exitosamente.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar el bovino porque tiene dependencias asociadas.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error inesperado.");
        }

    }




}
