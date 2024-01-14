package com.gandec.ganadecs.DTO.Ventas;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gandec.ganadecs.DTO.EntityDTO;
import com.gandec.ganadecs.Entity.Detalle_Venta;
import com.gandec.ganadecs.Entity.Venta;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class VentasDTO {
    private Venta venta;
    private List<Detalle_Venta> detalle_ventas=new ArrayList<>();


}
