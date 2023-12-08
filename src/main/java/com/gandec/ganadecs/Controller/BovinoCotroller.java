package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.BovinoDTO;
import com.gandec.ganadecs.Services.BovinoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/Ganadec/Bovinos")
public class BovinoCotroller {
    private final BovinoService bovinoService;




    @PostMapping("/save/{propietarioid}/{potreroId}")
    public ResponseEntity<String> SaveBovino(@PathVariable long propietarioid,@PathVariable long potreroId,
                                                @Valid @RequestBody BovinoDTO bovinoDTO){
       return new ResponseEntity<>(bovinoService.save(propietarioid,potreroId,bovinoDTO),HttpStatus.CREATED);

    }


    @GetMapping("/{number}")
    public ResponseEntity<BovinoDTO>GetBovinoId(@PathVariable(name = "number")String number){
        return ResponseEntity.ok(bovinoService.GetBovino(number));
    }
}
