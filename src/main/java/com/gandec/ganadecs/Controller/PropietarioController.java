package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.PropietarioDTO;
import com.gandec.ganadecs.DTO.PropietaryDTO;
import com.gandec.ganadecs.Entity.Propietario;
import com.gandec.ganadecs.Services.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Ganadec/Propietarios")
public class PropietarioController {
    @Autowired
    private PropietarioService propietarioService;
    @PostMapping
    public ResponseEntity<PropietarioDTO> SavePropietario(@RequestBody PropietarioDTO propietarioDTO){
        return new ResponseEntity<>(propietarioService.save(propietarioDTO), HttpStatus.CREATED);
    }
    @PostMapping("/save")
    public ResponseEntity<PropietaryDTO> savePropietary(@RequestBody PropietaryDTO propietaryDTO){
        return new ResponseEntity<>(propietarioService.saves(propietaryDTO),HttpStatus.CREATED);
    }
    @GetMapping("/GetAll")
    public ResponseEntity<List<PropietaryDTO>> GetAllPropietary(){
        List<PropietaryDTO> propietaryDTOList=propietarioService.PropietaryGetAll();
        return new ResponseEntity<>(propietaryDTOList,HttpStatus.OK);
   }
    @GetMapping
    public ResponseEntity<List<PropietarioDTO>>AllPropietary(){
        List<PropietarioDTO> propietarioDTOList = propietarioService.GetAll();
        return new ResponseEntity<>(propietarioDTOList,HttpStatus.OK);
    }
}
