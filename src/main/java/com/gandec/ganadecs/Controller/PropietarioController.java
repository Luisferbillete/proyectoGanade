package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.Auth.CreatePropietary;
import com.gandec.ganadecs.DTO.Propietary.PropietaryGetAll;
import com.gandec.ganadecs.DTO.PropietaryComboDto;
import com.gandec.ganadecs.DTO.PropietaryDTO;
import com.gandec.ganadecs.DTO.Util.Delete;
import com.gandec.ganadecs.Excepciones.UniqueConstraintException;
import com.gandec.ganadecs.Services.PropietarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/Ganadec/Propietarios")
public class PropietarioController {

    private final PropietarioService propietarioService;



    @GetMapping("/GetAll2")
    public Page<PropietaryGetAll> GetAllPropietary2(
            @RequestParam(name = "start") Integer start,
            @RequestParam(name = "limit") Integer limit) {
        return propietarioService.PropietaryGetAllPage(start, limit);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PropietaryDTO> GetPropietaryId(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(propietarioService.GetPropietary(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropietaryDTO> ActualizarPropietary(@Valid @RequestBody PropietaryDTO
                                                                      propietaryDTO, @PathVariable(name = "id")
                                                              long id) {
        PropietaryDTO updated = propietarioService.UpdatePropietary(propietaryDTO, id);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<String> DeletePropietary(@PathVariable(name = "id") long id) {
        propietarioService.DeletePropietary(id);
        return new ResponseEntity<>("Propietario Eliminado con exito", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> DeletePropietary2(@RequestBody Delete delete) {
        long id = delete.getId();
        propietarioService.DeletePropietary(id);
        return new ResponseEntity<>("Propietario Eliminado con exito", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PropietaryComboDto>> GetPropietary() {
        return ResponseEntity.ok(propietarioService.getPropietary());
    }

    @PostMapping("/save")

    public ResponseEntity<String> createPropietary(@Valid @RequestBody CreatePropietary createPropietary) throws UniqueConstraintException {
        propietarioService.save(createPropietary);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado exitosamente");


    }
}
