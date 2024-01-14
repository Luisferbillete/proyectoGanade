package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.Detalles_ventas.DetallesVentasDTO;

import java.util.List;

public interface DetallesVentaService {
    List<DetallesVentasDTO> findByVentaId(Long ventaId);
}
