package com.gandec.ganadecs.Services;


import com.gandec.ganadecs.DTO.Ventas.VentaClienteDetalleBovino;
import com.gandec.ganadecs.DTO.Ventas.VentaPropietaryClient;
import com.gandec.ganadecs.Entity.Bovino;
import com.gandec.ganadecs.Entity.Detalle_Venta;
import com.gandec.ganadecs.Entity.Venta;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VentaService {
    void save(Venta venta,List<Detalle_Venta>  detalle_ventas, long IdCliente);
    boolean BovinoVendido(Bovino bovino);
    List<VentaPropietaryClient> findAllByAnnoAndPropietarioIdOrderByFecha(int anno, Long propietarioId);
    List<VentaPropietaryClient>getAll();
    List<VentaPropietaryClient> getAllByFecha(LocalDate fecha);
    List<VentaPropietaryClient> getAllByLugar(String lugar);
    List<VentaClienteDetalleBovino> findDetailsForSoldBovino(String numero);
    List<VentaPropietaryClient> findByFechaBetween(LocalDate fecha1, LocalDate fecha2);
    List<VentaPropietaryClient> findByClienteIdAndFechaBetween(long clienteid, LocalDate fecha1, LocalDate fecha2);
    List<VentaPropietaryClient> findByCliente(long clienteId);
}
