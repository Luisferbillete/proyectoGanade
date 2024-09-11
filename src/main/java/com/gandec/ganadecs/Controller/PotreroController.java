package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.PotreroComboDto;
import com.gandec.ganadecs.DTO.PotreroDto;
import com.gandec.ganadecs.Services.PotterService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Ganadec/Potreros")

public class PotreroController {

    private final PotterService  potterService;

    public PotreroController(PotterService potterService) {
        this.potterService = potterService;
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<PotreroDto> SavePotter(@Valid @RequestBody PotreroDto potreroDto){
        return new ResponseEntity<>(potterService.Saves(potreroDto), HttpStatus.CREATED);
    }

    @GetMapping("/GetAll")
    public ResponseEntity<List<PotreroDto>> GetAllPotter(){
        List<PotreroDto> potreroDtoList=potterService.POTRERO_DTO_LIST();
        return  new ResponseEntity<>(potreroDtoList,HttpStatus.OK);
    }

    @GetMapping("/GetAllPage")
    public Page<PotreroDto> GetAllPotterPage3(
            @RequestParam(name = "start") Integer start,
            @RequestParam(name = "limit") Integer limit){
        return potterService.POTRERO_LIST_PAGE2(start,limit);
    }

    @GetMapping("/select")
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
