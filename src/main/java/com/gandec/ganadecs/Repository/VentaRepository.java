package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.DTO.Ventas.VentaClienteDetalleBovino;
import com.gandec.ganadecs.DTO.Ventas.VentaPropietaryClient;
import com.gandec.ganadecs.Entity.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    @Query("SELECT new com.gandec.ganadecs.DTO.Ventas.VentaPropietaryClient(v.Id,v.fecha,v.lugar,v.cliente.nombres,0) FROM Venta v " +
            "ORDER BY  v.fecha DESC ")
    List<VentaPropietaryClient> getAll();

    @Query("SELECT new com.gandec.ganadecs.DTO.Ventas.VentaPropietaryClient(v.Id, v.fecha, v.lugar, CONCAT(v.cliente.nombres,' ',v.cliente.apellidos) , " +
            "COALESCE(SUM(d.totalventa), 0)) " +
            "FROM Venta v " +
            "LEFT JOIN v.detalle_ventas d " +
            "GROUP BY v.Id, v.fecha, v.lugar, v.cliente.nombres " +
            "ORDER BY v.fecha DESC")
    Page<VentaPropietaryClient> getAllPage2(Pageable pageable);


    @Query("SELECT new com.gandec.ganadecs.DTO.Ventas.VentaPropietaryClient(v.Id,v.fecha,v.lugar,v.cliente.nombres,0) FROM Venta v JOIN v.detalle_ventas dv " +
            "WHERE YEAR(v.fecha) = :anno AND dv.bovino.propietario.id = :propietarioId " +
            "ORDER BY v.fecha desc ")
    List<VentaPropietaryClient> findAllByAnnoAndPropietarioIdOrderByFecha(
            @Param("anno") int anno, @Param("propietarioId") long propietarioId);

    @Query("SELECT new com.gandec.ganadecs.DTO.Ventas.VentaPropietaryClient(v.Id,v.fecha,v.lugar,v.cliente.nombres,0) " +
            "FROM Venta v where v.fecha = :fecha " +
            "ORDER BY  v.fecha DESC ")
    List<VentaPropietaryClient> getAllByFecha(@Param("fecha") LocalDate fecha);

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
    List<VentaPropietaryClient> getAllByLugar(@Param("lugar") String lugar);

    @Query("SELECT new com.gandec.ganadecs.DTO.Ventas.VentaPropietaryClient(v.Id,v.fecha,v.lugar,v.cliente.nombres,0) " +
            "FROM Venta v where v.cliente.Id= :clienteId " +
            "ORDER BY  v.fecha DESC ")
    List<VentaPropietaryClient> findByCliente(long clienteId);

    @Query("SELECT new com.gandec.ganadecs.DTO.Ventas.VentaClienteDetalleBovino(v.fecha,v.lugar,concat(v.cliente.nombres,' ',v.cliente.apellidos) ,dv.bovino.Numero,dv.peso,dv.precio,dv.totalventa,CONCAT(dv.bovino.propietario.nombres,' ',dv.bovino.propietario.apellidos),dv.tipodeventa,dv.bovino.sexo,dv.bovino.fecha_de_nacimiento ,' ')FROM Venta v " +
            "JOIN  v.detalle_ventas dv " +
            "JOIN  v.cliente c " +
            "JOIN  dv.bovino b " +
            "WHERE b.Numero = :numero")
    List<VentaClienteDetalleBovino> findDetailsForSoldBovino(@Param("numero") String numero);

    @Query("SELECT new com.gandec.ganadecs.DTO.Ventas.VentaClienteDetalleBovino(v.fecha,v.lugar,concat(v.cliente.nombres,' ',v.cliente.apellidos) ,dv.bovino.Numero,dv.peso,dv.precio,dv.totalventa,CONCAT(dv.bovino.propietario.nombres,' ',dv.bovino.propietario.apellidos),dv.tipodeventa,dv.bovino.sexo,dv.bovino.fecha_de_nacimiento ,' ')FROM Venta v " +
            "JOIN  v.detalle_ventas dv " +
            "JOIN  v.cliente c " +
            "JOIN  dv.bovino b " +
            "ORDER BY v.fecha DESC")
    Page<VentaClienteDetalleBovino> VentaBovinoGetAll(Pageable pageable);

    @Query("SELECT new com.gandec.ganadecs.DTO.Ventas.VentaClienteDetalleBovino(v.fecha,v.lugar,concat(v.cliente.nombres,' ',v.cliente.apellidos) ,dv.bovino.Numero,dv.peso,dv.precio,dv.totalventa,CONCAT(dv.bovino.propietario.nombres,' ',dv.bovino.propietario.apellidos),dv.tipodeventa,dv.bovino.sexo,dv.bovino.fecha_de_nacimiento ,' ')FROM Venta v " +
            "JOIN  v.detalle_ventas dv " +
            "JOIN  v.cliente c " +
            "JOIN  dv.bovino b " +
            "WHERE YEAR(v.fecha) = :anno")
    Page<VentaClienteDetalleBovino> VentaBovinoPorAño(@Param("anno") int anno, Pageable pageable);

    @Query("SELECT new com.gandec.ganadecs.DTO.Ventas.VentaClienteDetalleBovino(v.fecha,v.lugar,concat(v.cliente.nombres,' ',v.cliente.apellidos) ,dv.bovino.Numero,dv.peso,dv.precio,dv.totalventa,CONCAT(dv.bovino.propietario.nombres,' ',dv.bovino.propietario.apellidos),dv.tipodeventa,dv.bovino.sexo,dv.bovino.fecha_de_nacimiento ,' ')FROM Venta v " +
            "JOIN  v.detalle_ventas dv " +
            "JOIN  v.cliente c " +
            "JOIN  dv.bovino b " +
            "WHERE v.fecha = :fecha")
    Page<VentaClienteDetalleBovino> VentaBovinoPorFecha(@Param("fecha") LocalDate fecha, Pageable pageable);

    @Query("SELECT new com.gandec.ganadecs.DTO.Ventas.VentaClienteDetalleBovino(v.fecha,v.lugar,concat(v.cliente.nombres,' ',v.cliente.apellidos) ,dv.bovino.Numero,dv.peso,dv.precio,dv.totalventa,CONCAT(dv.bovino.propietario.nombres,' ',dv.bovino.propietario.apellidos),dv.tipodeventa,dv.bovino.sexo,dv.bovino.fecha_de_nacimiento ,' ')FROM Venta v " +
            "JOIN v.detalle_ventas dv " +
            "JOIN v.cliente c " +
            "JOIN dv.bovino b " +
            "WHERE v.fecha BETWEEN :startDate AND :endDate")
    Page<VentaClienteDetalleBovino> VentaBovinoEntreFechas(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable);

    /*@Query("SELECT new com.gandec.ganadecs.DTO.Ventas.VentaClienteDetalleBovino(v.fecha,v.lugar,concat(v.cliente.nombres,' ',v.cliente.apellidos), dv.bovino.Numero, dv.peso, dv.precio, dv.totalventa, CONCAT(dv.bovino.propietario.nombres,' ',dv.bovino.propietario.apellidos), dv.tipodeventa, dv.bovino.sexo, dv.bovino.fecha_de_nacimiento, ' ') FROM Venta v " +
            "JOIN v.detalle_ventas dv " +
            "JOIN v.cliente c " +
            "JOIN dv.bovino b " +
            "WHERE b.propietario.id = :idPropietario " +
            "ORDER BY v.fecha ASC")
    Page<VentaClienteDetalleBovino> VentaBovinoPorpropietario(@Param("idPropietario") Long idPropietario, Pageable pageable);
*/
    @Query("SELECT new com.gandec.ganadecs.DTO.Ventas.VentaClienteDetalleBovino(v.fecha, v.lugar, " +
            "concat(c.nombres, ' ', c.apellidos), b.Numero, dv.peso, dv.precio, " +
            "dv.totalventa, CONCAT(p.nombres, ' ', p.apellidos), " +
            "dv.tipodeventa, b.sexo, b.fecha_de_nacimiento, ' ') " +
            "FROM Venta v " +
            "JOIN v.cliente c " +
            "JOIN v.detalle_ventas dv " +
            "JOIN dv.bovino b " +
            "JOIN  b.propietario p " +
            "WHERE p.id = :idPropietario " +
            "ORDER BY v.fecha ASC")
    Page<VentaClienteDetalleBovino> VentaBovinoPorpropietario(@Param("idPropietario") Long idPropietario, Pageable pageable);

    @Query("SELECT new com.gandec.ganadecs.DTO.Ventas.VentaClienteDetalleBovino(v.fecha,v.lugar,concat(v.cliente.nombres,' ',v.cliente.apellidos), dv.bovino.Numero, dv.peso, dv.precio, dv.totalventa, CONCAT(dv.bovino.propietario.nombres,' ',dv.bovino.propietario.apellidos), dv.tipodeventa, dv.bovino.sexo, dv.bovino.fecha_de_nacimiento, ' ') FROM Venta v " +
            "JOIN v.detalle_ventas dv " +
            "JOIN v.cliente c " +
            "JOIN dv.bovino b " +
            "WHERE c.Id = :idComprador " +
            "ORDER BY v.fecha ASC")
        // Ordenar por fecha de venta
    Page<VentaClienteDetalleBovino> VentaBovinoPorComprador(@Param("idComprador") long idComprador, Pageable pageable);


}
