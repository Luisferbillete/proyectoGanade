package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.Ventas.VentaClienteDetalleBovino;
import com.gandec.ganadecs.DTO.Ventas.VentaPropietaryClient;
import com.gandec.ganadecs.DTO.Ventas.VentasDTO;
import com.gandec.ganadecs.Entity.Detalle_Venta;
import com.gandec.ganadecs.Entity.Venta;
import com.gandec.ganadecs.Services.VentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("Ganadec/venta")
public class VentaController {
    private final VentaService ventaService;
    @PostMapping("/save/{idCliente}")
    public ResponseEntity<String> save(@Valid @RequestBody VentasDTO ventadto, @PathVariable long idCliente){
        Venta venta=ventadto.getVenta();


        List<Detalle_Venta> detalle_ventas=ventadto.getDetalle_ventas();
        ventaService.save(venta,detalle_ventas,idCliente);
        return ResponseEntity.ok("Venta registrada");
    }
    @GetMapping("/ventabovinogetall")
    public Page<VentaClienteDetalleBovino> VentaBovinoGetAll(@RequestParam(name = "start") Integer start,
                                                             @RequestParam(name = "limit") Integer limit) {
    return ventaService.VentaBovinoGetAll(start, limit);
    }
    @GetMapping("/ventaporanno/{anno}")
    public  Page<VentaClienteDetalleBovino> VentaBovinoPorAnno(@PathVariable Integer anno,@RequestParam(name = "start") Integer start,
                                                               @RequestParam(name = "limit") Integer limit) {
    return  ventaService.VentaBovinoPorAnno(anno, start, limit);
    }
    @GetMapping("/ventaporfecha/{fecha}")
    public  Page<VentaClienteDetalleBovino> VentaBovinoPorFecha(@PathVariable LocalDate fecha,@RequestParam(name = "start") Integer start,
                                                               @RequestParam(name = "limit") Integer limit) {
        return  ventaService.VentaBovinoPorFecha(fecha, start, limit);
    }
    @GetMapping("/VentaBovinoEntreFechas/{startDate}/{endDate}")
    public Page<VentaClienteDetalleBovino> VentaBovinoEntreFechas(@PathVariable LocalDate startDate,
                                                                  @PathVariable LocalDate endDate,
                                                                  @RequestParam(name = "start")Integer  start,
                                                                  @RequestParam(name = "limit")Integer  limit) {
        return ventaService.VentaBovinoEntreFechas(startDate, endDate, start, limit);

    }

    @GetMapping("/getallpage")
    public Page<VentaPropietaryClient> getAllVenta(@RequestParam(name = "start") Integer start,
                                                   @RequestParam(name = "limit") Integer limit) {
        return ventaService.getAll(start, limit);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<VentaPropietaryClient>> getAll(){
        List<VentaPropietaryClient> ventaPropietaryClientList=ventaService.getAll();
        return ResponseEntity.ok(ventaPropietaryClientList);
    }
    @GetMapping("/getAllByFecha/{fecha}")
    public ResponseEntity<List<VentaPropietaryClient>> getAllByFecha(@PathVariable LocalDate fecha){
        List<VentaPropietaryClient> ventaPropietaryClientList=ventaService.getAllByFecha(fecha);
        return ResponseEntity.ok(ventaPropietaryClientList);
    }
    @GetMapping("/getAllLugar/{lugar}")
    public ResponseEntity<List<VentaPropietaryClient>> getAllFecha(@PathVariable String lugar){
        List<VentaPropietaryClient> ventaPropietaryClientList=ventaService.getAllByLugar(lugar);
        return ResponseEntity.ok(ventaPropietaryClientList);
    }
    @GetMapping("/findDetailsForSoldBovino/{numero}")
    public ResponseEntity<List<VentaClienteDetalleBovino>> findDetailsForSoldBovino(@PathVariable String numero){
        List<VentaClienteDetalleBovino> ventaClienteDetalleBovinos=ventaService.findDetailsForSoldBovino(numero);
        return ResponseEntity.ok(ventaClienteDetalleBovinos);
    }

    @GetMapping("/findAllByAnnoAndPropietarioIdOrderByFecha/{anno}/{propietarioId}")
    public ResponseEntity<List<VentaPropietaryClient>> findAllByAnnoAndPropietarioIdOrderByFecha(@PathVariable int anno, @PathVariable long propietarioId){
        List<VentaPropietaryClient> ventaPropietaryClientList=ventaService.findAllByAnnoAndPropietarioIdOrderByFecha(anno,propietarioId);
        return ResponseEntity.ok(ventaPropietaryClientList);
    }
    @GetMapping("/findByFechaBetween/{fecha1}/{fecha2}")
    public ResponseEntity<List<VentaPropietaryClient>> findByFechaBetween(@PathVariable LocalDate fecha1, @PathVariable LocalDate fecha2){
        List<VentaPropietaryClient> ventaPropietaryClientList=ventaService.findByFechaBetween(fecha1,fecha2);
        return ResponseEntity.ok(ventaPropietaryClientList);
    }
    @GetMapping("/findByClienteIdAndFechaBetween/{clienteid}/{fecha1}/{fecha2}")
    public ResponseEntity<List<VentaPropietaryClient>> findByClienteIdAndFechaBetween(@PathVariable long clienteid, @PathVariable LocalDate fecha1, @PathVariable LocalDate fecha2){
        List<VentaPropietaryClient> ventaPropietaryClientList=ventaService.findByClienteIdAndFechaBetween(clienteid,fecha1,fecha2);
        return ResponseEntity.ok(ventaPropietaryClientList);
    }
    @GetMapping("/findByCliente/{clienteId}")
    public ResponseEntity<List<VentaPropietaryClient>> findByCliente(@PathVariable long clienteId){
        List<VentaPropietaryClient> ventaPropietaryClientList=ventaService.findByCliente(clienteId);
        return ResponseEntity.ok(ventaPropietaryClientList);
    }


}
