package com.gandec.ganadecs.Controller;

import com.gandec.ganadecs.DTO.Detalles_venta_interna.DetalleVentaInternaDTO;
import com.gandec.ganadecs.Services.DetallesVentaInternaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Ganadec/detallesVentaInterna")
public class DetallesVentasInternaController {
    private final DetallesVentaInternaService detallesVentaInternaService;
    @GetMapping("/{ventainternaId}")
    public ResponseEntity<List<DetalleVentaInternaDTO>> findByVentaId(@PathVariable(name = "ventainternaId") Long ventainternaId) {
        return ResponseEntity.ok(detallesVentaInternaService.findByVentaInternaId(ventainternaId));
    }
}
