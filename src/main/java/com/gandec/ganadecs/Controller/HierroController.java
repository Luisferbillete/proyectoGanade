package com.gandec.ganadecs.Controller;


import com.gandec.ganadecs.Services.HierroService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Ganadec/Hierros")

public class HierroController {
    private final HierroService hierroService;

    @GetMapping("/all")
    public ResponseEntity<?> findAllHierros(){
        return new ResponseEntity<>(hierroService.findAllHierros(), HttpStatus.OK);
    }

    @GetMapping("/all/{propietaryId}")
    public ResponseEntity<?> findHierrosByPropietario(@PathVariable(name = "propietaryId") long propietaryId){
        return new ResponseEntity<>(hierroService.findHierrosByPropietario(propietaryId), HttpStatus.OK);
    }

    @PostMapping("/save/{propietaryId}")
    public ResponseEntity<String> save(
                                  @PathVariable(name = "propietaryId") long propietaryId,
                                  @RequestPart("file") MultipartFile file) throws IOException {
        hierroService.save(propietaryId, file);
        return new ResponseEntity<>("Archive subside excitement ! ", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable(name = "id") long id) throws IOException {
        hierroService.delete(id);
        return new ResponseEntity<>("Archive delete excitement ! ", HttpStatus.OK);
    }

}
