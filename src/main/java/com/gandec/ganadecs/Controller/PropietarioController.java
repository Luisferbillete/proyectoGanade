package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.PropietarioDTO;
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
    //@GetMapping
    //public ResponseEntity<List<Propietario>> GetAllPropietary(){
      //  List<Propietario> propietarios=propietarioService.Propietario_List();
        ////return new ResponseEntity<>(propietarios,HttpStatus.OK);
   // }
    @GetMapping
    public ResponseEntity<List<PropietarioDTO>>AllPropietary(){
        List<PropietarioDTO> propietarioDTOList = propietarioService.GetAll();
        return new ResponseEntity<>(propietarioDTOList,HttpStatus.OK);
    }
}
