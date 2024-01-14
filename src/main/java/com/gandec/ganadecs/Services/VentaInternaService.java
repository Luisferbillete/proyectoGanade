package com.gandec.ganadecs.Services;

import com.gandec.ganadecs.DTO.VentaInterna.VentaInternaCompradorVendedor;
import com.gandec.ganadecs.DTO.VentaInterna.VentaInternaCompradorVendedorBovino;
import com.gandec.ganadecs.Entity.Bovino;
import com.gandec.ganadecs.Entity.DetalleVentaInterna;
import com.gandec.ganadecs.Entity.Venta;
import com.gandec.ganadecs.Entity.VentaInterna;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VentaInternaService {
    boolean BovinoVendido(Bovino bovino);
    Optional<VentaInternaCompradorVendedorBovino> verifificarventaInternaAnterior(String numero,long propietarioId);
    void save(VentaInterna ventaInterna, List<DetalleVentaInterna> detalleVentaInternaList, long IdCliente, long IdPropietario);
    List<VentaInternaCompradorVendedor> getAll();
    List<VentaInternaCompradorVendedor> findAllByAnnoAndPropietarioIdOrderByFecha(int anno, long propietarioId);
    List<VentaInternaCompradorVendedor> findAllByPropietarioIdOrderByFecha(long propietarioId);
    List<VentaInternaCompradorVendedor> findByFecha(LocalDate fecha);
    List<VentaInternaCompradorVendedor> findByFechaBetween(LocalDate fecha1, LocalDate fecha2);
    List<VentaInternaCompradorVendedor> findByPropietarioIdAndFechaBetween(long propietarioId, LocalDate fecha1, LocalDate fecha2);
    List<VentaInternaCompradorVendedor>  findByCliente(long clienteId);
    List<VentaInternaCompradorVendedorBovino> findByDetalle_ventas_internasBovinoNumero(String numero);
}
