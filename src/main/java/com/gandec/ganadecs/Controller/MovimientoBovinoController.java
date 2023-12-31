package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.MovimientoBovinoDTO;
import com.gandec.ganadecs.DTO.MovimientosDTO;
import com.gandec.ganadecs.Entity.MovimientoBovino;
import com.gandec.ganadecs.Entity.Potrero;
import com.gandec.ganadecs.Excepciones.equalPaddocks;
import com.gandec.ganadecs.Services.MovimientoBovinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Ganadec/Movimientos")
public class MovimientoBovinoController {
    private final MovimientoBovinoService movimientoBovinoService;

    @GetMapping("/get/{potrero}")
    public ResponseEntity<List<MovimientoBovinoDTO>>getMovimientos(@PathVariable long potrero){
        List<MovimientoBovinoDTO> movimientoBovinoDTOList=movimientoBovinoService.getMovimientoBovinosPorPotrero(potrero);
        return new ResponseEntity<>(movimientoBovinoDTOList,HttpStatus.OK);
    }
    /*
    @GetMapping("/gettraslado/{potrero}")
    public ResponseEntity<List<MovimientoBovinoDTO>>getTraslado(@PathVariable long potrero){
        List<MovimientoBovinoDTO> movimientoBovinoDTOList=movimientoBovinoService.TraladoPotter(potrero);
        return new ResponseEntity<>(movimientoBovinoDTOList,HttpStatus.OK);
    }*/
   /*@GetMapping("/getconvercion/{potrero}")
    public ResponseEntity<List<MovimientoBovino>>getconvercion(@PathVariable long potrero){
        List<MovimientoBovino> movimientoBovinoList=movimientoBovinoService.convertirEntity(potrero);
        return new ResponseEntity<>(movimientoBovinoList,HttpStatus.OK);
    }*/

    @GetMapping("/getBybovinoByPotter/{numero}")
    @ResponseStatus(HttpStatus.OK)
    public List<MovimientoBovinoDTO> getMovimientoBovino(@PathVariable String numero){
        return movimientoBovinoService.findByMovimientosBovino(numero);
    }
   /* @PutMapping("/{potrero}")
    @ResponseStatus(HttpStatus.OK)
    public int UpdateBovinosPotrero(@PathVariable Potrero potrero){
        return movimientoBovinoService.UpdateBovinosPotrero(potrero);

    }
    @PutMapping("update/{numeroId}")
    @ResponseStatus(HttpStatus.OK)
    public int UpdatePotrero(@PathVariable String numeroId ){
        return movimientoBovinoService.probarbovino(numeroId);

    }*/
    /*mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm*/
    @GetMapping("/getAllBovineByPaddock")
    public List<MovimientosDTO> getAllBovinesBypaddock(){
        return movimientoBovinoService.getAllBovinosBypaddock();
    }
    @PostMapping("/traslado/{potreroIdOrigen}/{potreroIdDestino}")
    public ResponseEntity<String> saveTraslado(@PathVariable long potreroIdOrigen,
                               @PathVariable long potreroIdDestino){
        if(potreroIdOrigen==potreroIdDestino){
            throw new equalPaddocks("El potrero orige no puede ser el mismo que el potrero destino");

        }else {
            movimientoBovinoService.trasladar(potreroIdOrigen, potreroIdDestino);
        }
        return ResponseEntity.ok("Traslado  exitosamente.");

    }
    @PostMapping("/trasladarBovino/{numeroId}/{potreroIdDestino}")
    public ResponseEntity<String>trasladarBovino(@PathVariable String numeroId,@PathVariable long potreroIdDestino){
        movimientoBovinoService.trasladarBovino(numeroId,potreroIdDestino);
        return ResponseEntity.ok("Traslado del animal exitoso.");
    }
}
