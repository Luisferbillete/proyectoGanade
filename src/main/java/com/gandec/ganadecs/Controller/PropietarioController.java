package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.PropietaryComboDto;
import com.gandec.ganadecs.DTO.PropietaryDTO;
import com.gandec.ganadecs.Services.PropietarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Ganadec/Propietarios")
public class PropietarioController {

    private final PropietarioService propietarioService;

    public PropietarioController(PropietarioService propietarioService) {
        this.propietarioService = propietarioService;
    }

    @PostMapping("/save")
    public ResponseEntity<PropietaryDTO> savePropietary(@Valid @RequestBody PropietaryDTO propietaryDTO){
        return new ResponseEntity<>(propietarioService.saves(propietaryDTO),HttpStatus.CREATED);
    }
    @GetMapping("/GetAll")
    public ResponseEntity<List<PropietaryDTO>> GetAllPropietary(){
        List<PropietaryDTO> propietaryDTOList=propietarioService.PropietaryGetAll();
        return new ResponseEntity<>(propietaryDTOList,HttpStatus.OK);
   }
   @GetMapping("/combo")
   public ResponseEntity<List<PropietaryComboDto>> GetAllcombo(){
        List<PropietaryComboDto> propietaryComboDtoList=
                propietarioService.PROPIETARY_COMBO_DTO_LIST()
        ;
        return  new ResponseEntity<>(propietaryComboDtoList,HttpStatus.OK);
   }
   @GetMapping("/{id}")
   public ResponseEntity<PropietaryDTO> GetPropietaryId(@PathVariable(name = "id")long id){
        return ResponseEntity.ok(propietarioService.GetPropietary(id));
   }
   @PutMapping("/{id}")
   public ResponseEntity<PropietaryDTO> ActualizarPropietary(@Valid @RequestBody PropietaryDTO
                                                             propietaryDTO,@PathVariable(name="id")
                                                             long id){
        PropietaryDTO updated=propietarioService.UpdatePropietary(propietaryDTO, id);
        return new ResponseEntity<>(updated,HttpStatus.OK);
   }
   @DeleteMapping("/{id}")
   public ResponseEntity<String> DeletePropietary(@PathVariable(name = "id")long id){
        propietarioService.DeletePropietary(id);
        return new ResponseEntity<>("Propietario Eliminado con exito",HttpStatus.OK);
   }
   @GetMapping("/search/{nombres}/{apellidos}")
    public ResponseEntity<PropietaryComboDto> SearchPropietary(@PathVariable(name = "nombres")String nombres,
                                                             @PathVariable(name = "apellidos")String apellidos){
          return ResponseEntity.ok(propietarioService.findPropietaryComboDtoByNombresAndApellidos(nombres,apellidos));
    }

}
