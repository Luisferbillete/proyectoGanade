package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.Detalles_ventas.DetallesVentasDTO;
import com.gandec.ganadecs.Services.DetallesVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Ganadec/DetallesVentas")
public class DetallesVentasController {
    private final DetallesVentaService detallesVentaService;
    @RequestMapping("/DetallesDeVenta/{ventaId}")
    public ResponseEntity<List<DetallesVentasDTO>> findByVentaId(@PathVariable(name = "ventaId") Long ventaId) {
        return ResponseEntity.ok(detallesVentaService.findByVentaId(ventaId));
    }
}
