package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.DTO.Ventas.VentaClienteDetalleBovino;
import com.gandec.ganadecs.DTO.Ventas.VentaPropietaryClient;
import com.gandec.ganadecs.Entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface VentaRepository extends JpaRepository<Venta, String> {
    @Query("SELECT new com.gandec.ganadecs.DTO.Ventas.VentaPropietaryClient(v.Id,v.fecha,v.lugar,v.cliente.nombres,0) FROM Venta v " +
            "ORDER BY  v.fecha DESC ")
    List<VentaPropietaryClient> getAll();
    @Query("SELECT new com.gandec.ganadecs.DTO.Ventas.VentaPropietaryClient(v.Id,v.fecha,v.lugar,v.cliente.nombres,0) FROM Venta v JOIN v.detalle_ventas dv " +
            "WHERE YEAR(v.fecha) = :anno AND dv.bovino.propietario.id = :propietarioId " +
            "ORDER BY v.fecha desc ")
    List<VentaPropietaryClient> findAllByAnnoAndPropietarioIdOrderByFecha(
            @Param("anno") int anno, @Param("propietarioId") long propietarioId);
    @Query("SELECT new com.gandec.ganadecs.DTO.Ventas.VentaPropietaryClient(v.Id,v.fecha,v.lugar,v.cliente.nombres,0) " +
            "FROM Venta v where v.fecha = :fecha " +
            "ORDER BY  v.fecha DESC ")
    List<VentaPropietaryClient> getAllByFecha(@Param("fecha")LocalDate fecha);
    @Query("SELECT new com.gandec.ganadecs.DTO.Ventas.VentaPropietaryClient(v.Id,v.fecha,v.lugar,v.cliente.nombres,0) " +
            "FROM Venta v where v.fecha BETWEEN :fecha1 AND :fecha2 " +
            "ORDER BY  v.fecha DESC ")
    List<VentaPropietaryClient> findByFechaBetween(LocalDate fecha1, LocalDate fecha2);
    @Query("SELECT new com.gandec.ganadecs.DTO.Ventas.VentaPropietaryClient(v.Id,v.fecha,v.lugar,v.cliente.nombres,0) " +
            "FROM Venta v where v.cliente.Id = :clienteid AND v.fecha BETWEEN :fecha1 AND :fecha2 " +
            "ORDER BY  v.fecha DESC ")
    List<VentaPropietaryClient> findByClienteIdAndFechaBetween(long clienteid, LocalDate fecha1, LocalDate fecha2);

    @Query("SELECT new com.gandec.ganadecs.DTO.Ventas.VentaPropietaryClient(v.Id,v.fecha,v.lugar,v.cliente.nombres,0) " +
            "FROM Venta v where v.lugar= :lugar " +
            "ORDER BY  v.fecha DESC ")
    List<VentaPropietaryClient> getAllByLugar(@Param("lugar")String lugar);
    @Query("SELECT new com.gandec.ganadecs.DTO.Ventas.VentaPropietaryClient(v.Id,v.fecha,v.lugar,v.cliente.nombres,0) " +
            "FROM Venta v where v.cliente.Id= :clienteId " +
            "ORDER BY  v.fecha DESC ")
    List<VentaPropietaryClient> findByCliente(long clienteId);

    @Query("SELECT new com.gandec.ganadecs.DTO.Ventas.VentaClienteDetalleBovino(v.fecha,v.lugar,v.cliente.nombres,dv.bovino.Numero,dv.peso,dv.precio,dv.peso*dv.precio,' ')FROM Venta v " +
            "JOIN  v.detalle_ventas dv " +
            "JOIN  v.cliente c " +
            "JOIN  dv.bovino b " +
            "WHERE b.Numero = :numero")
    List<VentaClienteDetalleBovino> findDetailsForSoldBovino(@Param("numero") String numero);


}
