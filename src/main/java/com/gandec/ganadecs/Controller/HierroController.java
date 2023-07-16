package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.HierroDto;
import com.gandec.ganadecs.Services.HierroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Ganadec/Hierros")
public class HierroController {
    private final HierroService hierroService;

    public HierroController(HierroService hierroService) {
        this.hierroService = hierroService;
    }
    @PostMapping("/save/{propietaryId}")
    public ResponseEntity<HierroDto> SaveHierro(@PathVariable(name = "propietaryId")long propietaryId
                                               , @Valid @RequestBody HierroDto hierroDto){
        return new ResponseEntity<>(hierroService.save(propietaryId,hierroDto), HttpStatus.CREATED);
    }
}
