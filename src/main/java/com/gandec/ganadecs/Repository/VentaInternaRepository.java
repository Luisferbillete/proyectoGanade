package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.DTO.VentaInterna.VentaInternaCompradorVendedor;
import com.gandec.ganadecs.DTO.VentaInterna.VentaInternaCompradorVendedorBovino;
import com.gandec.ganadecs.Entity.VentaInterna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VentaInternaRepository extends JpaRepository<VentaInterna, Long> {
    @Query("SELECT new com.gandec.ganadecs.DTO.VentaInterna.VentaInternaCompradorVendedor" +
            "(v.Id,v.fecha,concat( v.cliente.nombres,' ',v.cliente.apellidos)," +
            "concat(v.propietario.nombres,' ',v.propietario.apellidos),0 ) FROM VentaInterna v ")
    List<VentaInternaCompradorVendedor> getAll();

    @Query("SELECT new com.gandec.ganadecs.DTO.VentaInterna.VentaInternaCompradorVendedor" +
            "(v.Id,v.fecha,concat( v.cliente.nombres,' ',v.cliente.apellidos)," +
            "concat(v.propietario.nombres,' ',v.propietario.apellidos),0 ) FROM VentaInterna v " +
            "WHERE YEAR(v.fecha) = :anno AND v.propietario.id = :propietarioId " +
            "ORDER BY v.fecha desc ")
    List<VentaInternaCompradorVendedor> findAllByAnnoAndPropietarioIdOrderByFecha(
            int anno, long propietarioId);
    @Query("SELECT new com.gandec.ganadecs.DTO.VentaInterna.VentaInternaCompradorVendedor" +
            "(v.Id,v.fecha,concat( v.cliente.nombres,' ',v.cliente.apellidos)," +
            "concat(v.propietario.nombres,' ',v.propietario.apellidos),0 ) FROM VentaInterna v " +
            "WHERE v.propietario.id = :propietarioId " +
            "ORDER BY v.fecha desc ")
    List<VentaInternaCompradorVendedor> findAllByPropietarioIdOrderByFecha(long propietarioId);
    @Query("SELECT new com.gandec.ganadecs.DTO.VentaInterna.VentaInternaCompradorVendedor" +
            "(v.Id,v.fecha,concat( v.cliente.nombres,' ',v.cliente.apellidos)," +
            "concat(v.propietario.nombres,' ',v.propietario.apellidos),0 ) FROM VentaInterna v " +
            "WHERE v.fecha = :fecha " +
            "ORDER BY v.fecha desc ")
    List<VentaInternaCompradorVendedor> findByFecha(LocalDate fecha);
    @Query("SELECT new com.gandec.ganadecs.DTO.VentaInterna.VentaInternaCompradorVendedor" +
            "(v.Id,v.fecha,concat( v.cliente.nombres,' ',v.cliente.apellidos)," +
            "concat(v.propietario.nombres,' ',v.propietario.apellidos),0 ) FROM VentaInterna v " +
            "WHERE v.fecha BETWEEN :fecha1 AND :fecha2 " +
            "ORDER BY v.fecha desc ")
    List<VentaInternaCompradorVendedor> findByFechaBetween(LocalDate fecha1, LocalDate fecha2);

    @Query("SELECT new com.gandec.ganadecs.DTO.VentaInterna.VentaInternaCompradorVendedor" +
            "(v.Id,v.fecha,concat( v.cliente.nombres,' ',v.cliente.apellidos)," +
            "concat(v.propietario.nombres,' ',v.propietario.apellidos),0 ) FROM VentaInterna v " +
            "WHERE v.propietario.id = :propietarioId AND v.fecha BETWEEN :fecha1 AND :fecha2 " +
            "ORDER BY v.fecha desc ")
    List<VentaInternaCompradorVendedor> findByPropietarioIdAndFechaBetween(long propietarioId, LocalDate fecha1, LocalDate fecha2);
    @Query("SELECT new com.gandec.ganadecs.DTO.VentaInterna.VentaInternaCompradorVendedor" +
            "(v.Id,v.fecha,concat( v.cliente.nombres,' ',v.cliente.apellidos)," +
            "concat(v.propietario.nombres,' ',v.propietario.apellidos),0 ) FROM VentaInterna v " +
            "WHERE v.cliente.Id = :clienteId " +
            "ORDER BY v.fecha desc ")
    List<VentaInternaCompradorVendedor> findByCliente(long clienteId);
    @Query("SELECT new com.gandec.ganadecs.DTO.VentaInterna.VentaInternaCompradorVendedorBovino" +
            "(v.fecha,concat( v.cliente.nombres,' ',v.cliente.apellidos)," +
            "concat(v.propietario.nombres,' ',v.propietario.apellidos),dv.bovino.Numero,dv.peso,dv.precio,dv.peso *dv.precio,' ') FROM VentaInterna v " +
            "JOIN v.detalle_ventas_internas dv " +
            "WHERE dv.bovino.Numero = :numero " +
            "ORDER BY v.fecha desc ")
   List<VentaInternaCompradorVendedorBovino> findByDetalle_ventas_internasBovinoNumero(String numero);

    @Query("SELECT new com.gandec.ganadecs.DTO.VentaInterna.VentaInternaCompradorVendedorBovino" +
            "(v.fecha,concat( v.cliente.nombres,' ',v.cliente.apellidos)," +
            "concat(v.propietario.nombres,' ',v.propietario.apellidos),dv.bovino.Numero,dv.peso,dv.precio,dv.peso *dv.precio,' ') FROM VentaInterna v " +
            "JOIN v.detalle_ventas_internas dv " +
            "WHERE dv.bovino.Numero = :numero and v.propietario.id =:propietarioId")
    Optional<VentaInternaCompradorVendedorBovino> verifificarventaInternaAnterior (String numero , long propietarioId);

}
