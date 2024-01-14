package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.VentaInterna.VentaInternaCompradorVendedor;
import com.gandec.ganadecs.DTO.VentaInterna.VentaInternaCompradorVendedorBovino;
import com.gandec.ganadecs.DTO.Ventas.VentaInternaDTO;
import com.gandec.ganadecs.Entity.DetalleVentaInterna;
import com.gandec.ganadecs.Entity.Venta;
import com.gandec.ganadecs.Entity.VentaInterna;
import com.gandec.ganadecs.Services.VentaInternaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Ganadec/ventaInterna")
public class VentaInternaController {
    private final VentaInternaService ventaInternaService;
    @GetMapping("/getAll")
    public ResponseEntity<List<VentaInternaCompradorVendedor>> getAll(){
        return ResponseEntity.ok(ventaInternaService.getAll());
    }
    @GetMapping("/getAll/{anno}/{idPropietario}")
    public ResponseEntity<List<VentaInternaCompradorVendedor>> getAll(@PathVariable int anno,
                                                                      @PathVariable long idPropietario){
        return ResponseEntity.ok(ventaInternaService.findAllByAnnoAndPropietarioIdOrderByFecha(anno,idPropietario));
    }
    @GetMapping("/getAll/{idPropietario}")
    public ResponseEntity<List<VentaInternaCompradorVendedor>> getAll(@PathVariable long idPropietario){
        return ResponseEntity.ok(ventaInternaService.findAllByPropietarioIdOrderByFecha(idPropietario));
    }
    @GetMapping("/getByFecha/{fecha}")
    public ResponseEntity<List<VentaInternaCompradorVendedor>> getByFecha(@PathVariable LocalDate fecha){
        return ResponseEntity.ok(ventaInternaService.findByFecha(fecha));
    }
    @GetMapping("/getByFechaBetween/{fecha1}/{fecha2}")
    public ResponseEntity<List<VentaInternaCompradorVendedor>> getByFechaBetween(@PathVariable LocalDate fecha1,
                                                                                 @PathVariable LocalDate fecha2){
        return ResponseEntity.ok(ventaInternaService.findByFechaBetween(fecha1,fecha2));
    }
    @GetMapping("/getByFechaBetween/{idPropietario}/{fecha1}/{fecha2}")
    public ResponseEntity<List<VentaInternaCompradorVendedor>> getByFechaBetween(@PathVariable long idPropietario,
                                                                                 @PathVariable LocalDate fecha1,
                                                                                 @PathVariable LocalDate fecha2){
        return ResponseEntity.ok(ventaInternaService.findByPropietarioIdAndFechaBetween(idPropietario,fecha1,fecha2));
    }
    @GetMapping("/getByCliente/{idCliente}")
    public ResponseEntity<List<VentaInternaCompradorVendedor>> GetfindByCliente(@PathVariable long idCliente){
        return ResponseEntity.ok(ventaInternaService.findByCliente(idCliente));
    }
    @GetMapping("/getByBovino/{numero}")
    public ResponseEntity<List<VentaInternaCompradorVendedorBovino>> GetfindByBovino(@PathVariable String numero){
        return ResponseEntity.ok(ventaInternaService.findByDetalle_ventas_internasBovinoNumero(numero));
    }
    @PostMapping("/save/{idCliente}/{idPropietario}")
    public ResponseEntity<String> save(@Valid @RequestBody VentaInternaDTO ventaInternaDTO,
                                       @PathVariable long idCliente,
                                       @PathVariable long idPropietario){
        VentaInterna ventaInterna=ventaInternaDTO.getVentaInterna();
        List<DetalleVentaInterna> detalleVentaInternaList=ventaInternaDTO.getDetalleVentaInternaList();
        ventaInternaService.save(ventaInterna,detalleVentaInternaList,idCliente,idPropietario);
        return ResponseEntity.ok("Venta interna registrada");
    }



}
