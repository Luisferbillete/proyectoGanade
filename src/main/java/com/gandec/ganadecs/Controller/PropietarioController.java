package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.Entity.Propietario;
import com.gandec.ganadecs.Services.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Ganadec/Propietarios")
public class PropietarioController {
    @Autowired
    private PropietarioService propietarioService;
    @PostMapping
    public ResponseEntity<Propietario> SavePropietario(@RequestBody Propietario propietario){
        return new ResponseEntity<>(propietarioService.SavePropietario(propietario), HttpStatus.CREATED);
    }
}
