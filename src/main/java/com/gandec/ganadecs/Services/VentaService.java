package com.gandec.ganadecs.Services;


import com.gandec.ganadecs.DTO.Ventas.VentaClienteDetalleBovino;
import com.gandec.ganadecs.DTO.Ventas.VentaPropietaryClient;
import com.gandec.ganadecs.Entity.Bovino;
import com.gandec.ganadecs.Entity.Detalle_Venta;
import com.gandec.ganadecs.Entity.Venta;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface VentaService {
    void save(Venta venta, List<Detalle_Venta> detalle_ventas, long IdCliente);

    boolean BovinoVendido(Bovino bovino);

    List<VentaPropietaryClient> findAllByAnnoAndPropietarioIdOrderByFecha(int anno, Long propietarioId);

    List<VentaPropietaryClient> getAll();

    Page<VentaClienteDetalleBovino> VentaBovinoGetAll(Integer Start, Integer limit);

    Page<VentaClienteDetalleBovino> VentaBovinoPorAnno(Integer anno, Integer start, Integer limit);

    Page<VentaClienteDetalleBovino> VentaBovinoPorFecha(LocalDate fecha, Integer start, Integer limit);

    Page<VentaPropietaryClient> getAll(Integer start, Integer limit);

    Page<VentaClienteDetalleBovino> VentaBovinoEntreFechas(LocalDate startDate, LocalDate endDate, Integer start, Integer limit);

    Page<VentaClienteDetalleBovino> VentaBovinoPorpropietario(Long idPropietario, Integer start, Integer limit);

    Page<VentaClienteDetalleBovino> VentaBovinoPorComprador(Long idComprador, Integer start, Integer limit);

    List<VentaPropietaryClient> getAllByFecha(LocalDate fecha);

    List<VentaPropietaryClient> getAllByLugar(String lugar);

    List<VentaClienteDetalleBovino> findDetailsForSoldBovino(String numero);

    List<VentaPropietaryClient> findByFechaBetween(LocalDate fecha1, LocalDate fecha2);

    List<VentaPropietaryClient> findByClienteIdAndFechaBetween(long clienteid, LocalDate fecha1, LocalDate fecha2);

    List<VentaPropietaryClient> findByCliente(long clienteId);
}
