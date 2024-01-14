package com.gandec.ganadecs.DTO.Ventas;

import com.gandec.ganadecs.Entity.DetalleVentaInterna;
import com.gandec.ganadecs.Entity.VentaInterna;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
public class VentaInternaDTO {
    private VentaInterna ventaInterna;
    private List<DetalleVentaInterna> detalleVentaInternaList=new ArrayList<>();
}
