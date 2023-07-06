package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.BovinoDTO;
import com.gandec.ganadecs.DTO.PotreroDto;
import com.gandec.ganadecs.Services.BovinoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Ganadec/Bovinos")
public class BovinoCotroller {
    @Autowired
    BovinoService bovinoService;
    @PostMapping("/save")
    public ResponseEntity<BovinoDTO> SaveBovino(@Valid @RequestBody BovinoDTO bovinoDTO){
        return new ResponseEntity<>(bovinoService.save(bovinoDTO), HttpStatus.CREATED);
    }
}
