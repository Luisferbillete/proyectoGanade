package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.PotreroComboDto;
import com.gandec.ganadecs.DTO.PotreroDto;
import com.gandec.ganadecs.Services.PotterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Ganadec/Potreros")

public class PotreroController {
    @Autowired
    PotterService potterService;
    @PostMapping("/save")
    public ResponseEntity<PotreroDto> SavePotter(@Valid @RequestBody PotreroDto potreroDto){
        return new ResponseEntity<>(potterService.Saves(potreroDto), HttpStatus.CREATED);
    }
    @GetMapping("/GetAll")
    public ResponseEntity<List<PotreroDto>> GetAllPotter(){
        List<PotreroDto> potreroDtoList=potterService.POTRERO_DTO_LIST();
        return  new ResponseEntity<>(potreroDtoList,HttpStatus.OK);
    }
    @GetMapping("/combo")
    public ResponseEntity<List<PotreroComboDto>>GetAllPotterCombo(){
        List<PotreroComboDto> potreroComboDtoList=potterService.POTRERO_COMBO_DTO_LIST();
        return new ResponseEntity<>(potreroComboDtoList,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PotreroDto> GetPotterId(@PathVariable(name = "id")long id){
        return ResponseEntity.ok(potterService.GetPotter(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PotreroDto> UpdatePotter(@Valid @RequestBody PotreroDto potreroDto,
                                                   @PathVariable(name = "id") long id){
        PotreroDto Update=potterService.UpdatePotter(potreroDto,id);
        return new ResponseEntity<>(Update,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeletePotter(@PathVariable(name = "id")long id){
        potterService.DeletePotrero(id);
        return new ResponseEntity<>("Potrero Eliminado con Exito",HttpStatus.OK);
    }
}
