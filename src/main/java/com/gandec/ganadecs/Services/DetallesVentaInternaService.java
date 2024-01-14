package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.Detalles_venta_interna.DetalleVentaInternaDTO;

import java.util.List;

public interface DetallesVentaInternaService {
    List<DetalleVentaInternaDTO> findByVentaInternaId(Long ventaInternaId);
}
