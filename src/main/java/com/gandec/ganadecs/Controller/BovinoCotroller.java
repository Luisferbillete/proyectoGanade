package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.BovinoDTO;
import com.gandec.ganadecs.Services.BovinoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Ganadec/Bovinos")
public class BovinoCotroller {
    private final BovinoService bovinoService;

    public BovinoCotroller(BovinoService bovinoService) {
        this.bovinoService = bovinoService;
    }

    @PostMapping("/save/{propietarioid}/{potreroId}")
    public ResponseEntity<BovinoDTO> SaveBovino(@PathVariable long propietarioid,@PathVariable long potreroId,
                                                @Valid @RequestBody BovinoDTO bovinoDTO){
       return new ResponseEntity<>(bovinoService.save(propietarioid,potreroId,bovinoDTO),HttpStatus.CREATED);
    }
    @PostMapping("/guardar")
    public ResponseEntity<BovinoDTO> SaveBovinos(@Valid @RequestBody BovinoDTO bovinoDTO){
        return new ResponseEntity<>(bovinoService.saves(bovinoDTO),HttpStatus.CREATED);
    }
    @GetMapping("/getall")
    public ResponseEntity<List<BovinoDTO>> GetAll(){
        List<BovinoDTO>bovinoDTOList=bovinoService.BOVINO_DTO_LIST();
        return new ResponseEntity<>(bovinoDTOList,HttpStatus.OK);
    }
    @GetMapping("/{number}")
    public ResponseEntity<BovinoDTO>GetBovinoId(@PathVariable(name = "number")String number){
        return ResponseEntity.ok(bovinoService.GetBovino(number));
    }
}
