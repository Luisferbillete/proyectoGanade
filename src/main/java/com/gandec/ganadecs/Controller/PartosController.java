package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.Parto.*;

import com.gandec.ganadecs.Services.PartoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Ganadec/Partos")
public class PartosController {
    private final PartoService partoService;

    @GetMapping("/get/{numero}") public ResponseEntity<PartosCriasUpdate> getparto(@PathVariable(name = "numero") String numero){
        Optional<PartosCriasUpdate> partosCriasUpdateOptional = partoService.getPartosCrias(numero);
        return partosCriasUpdateOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/all")
    public Page<PartosPropietariosDTO> GetPartosAll(@RequestParam(name = "start") Integer start,
                                             @RequestParam(name = "limit") Integer limit) {
        return partoService.getPartosAll(start, limit);
    }


    @PostMapping("/save/{bovinoId}")
    public ResponseEntity<String> savePartos(@Valid @RequestBody Birthsdto birthsDTO,
                                             @PathVariable String bovinoId) {

        partoService.save(birthsDTO,bovinoId);
        return ResponseEntity.ok("Parto Registrado");
    }

    @GetMapping("/findPartosAndPropietarioWithNullDestete")
    public ResponseEntity<List<PartosPropietariosDTO>> findPartosAndPropietarioWithNullDestete(){
        return ResponseEntity.ok(partoService.findPartosAndPropietarioWithNullDestete());


    }
    @GetMapping("/findPartosAndPropietarioDTOWithNullDesteteAndPropietarioId/{propietarioId}")
    public ResponseEntity<List<PartosFindPropietarioDTO>> findPartosAndPropietarioDTOWithNullDesteteAndPropietarioId
            (@PathVariable Long propietarioId){
        return ResponseEntity.ok(partoService.findPartosAndPropietarioDTOWithNullDesteteAndPropietarioId(propietarioId));

    }

    @GetMapping("/FinAllPartosPorSexoCria/{sexoCria}")
    public ResponseEntity<List<PartosPorPropietarioYCriasYSexo>> PartosPorPropietarioYCriasYSexo(@PathVariable String sexoCria){
        return ResponseEntity.ok(partoService.FinAllPartosPorSexoCria(sexoCria));

    }
    @GetMapping("/FinAllPartosPorSexoCriaYPropietary/{sexoCria}/{propietarioId}")
    public ResponseEntity<List<PartosPorPropietarioYCriasYSexo>> FinAllPartosPorSexoCriaYPropietary(@PathVariable String sexoCria,
                                                                                                    @PathVariable Long propietarioId ){
        return ResponseEntity.ok(partoService.FinAllPartosPorSexoCriaYPropietary(sexoCria,propietarioId));

    }
    @PutMapping("/update/{numero}")
    public ResponseEntity<String> ActualizarParto(@Valid @RequestBody Birthsdto birthsDTO,
                                                 @PathVariable String numero) {

        partoService.ActualizarParto(birthsDTO,numero);
        return ResponseEntity.ok("Parto Actualizado");
    }
    @DeleteMapping("/delete/{numero}")
    public ResponseEntity<String> delete(@PathVariable(name = "numero") String numero) {

        try {
            partoService.deleteParto(numero);
            return ResponseEntity.ok("Parto eliminado exitosamente.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar el parto porque tiene registros asociados.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error inesperado.");
        }

    }




}
