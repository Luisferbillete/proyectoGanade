package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.MovimientoBovinoDTO;
import com.gandec.ganadecs.DTO.MovimientosDTO;
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


    @GetMapping("/getBybovinoByPotter/{numero}")
    @ResponseStatus(HttpStatus.OK)
    public List<MovimientoBovinoDTO> getMovimientoBovino(@PathVariable String numero){
        return movimientoBovinoService.findByMovimientosBovino(numero);
    }


    @GetMapping("/getAllBovineByPaddock")
    public List<MovimientosDTO> getAllBovinesBypaddock(){
        return movimientoBovinoService.getAllBovinosBypaddock();
    }
    @PostMapping("/traslado/{potreroIdOrigen}/{potreroIdDestino}")
    public ResponseEntity<String> saveTraslado(@PathVariable long potreroIdOrigen,
                               @PathVariable long potreroIdDestino){
        if(potreroIdOrigen==potreroIdDestino){
            throw new equalPaddocks("El potrero origen igual que el potrero destino");

        }else {
            movimientoBovinoService.trasladar(potreroIdOrigen, potreroIdDestino);
        }
        return ResponseEntity.ok("Traslado  Exitoso.");

    }
    @PostMapping("/trasladarBovino/{numeroId}/{potreroIdDestino}")
    public ResponseEntity<String>trasladarBovino(@PathVariable String numeroId,@PathVariable long potreroIdDestino){
        movimientoBovinoService.trasladarBovino(numeroId,potreroIdDestino);
        return ResponseEntity.ok("Traslado del animal exitoso.");
    }
}
