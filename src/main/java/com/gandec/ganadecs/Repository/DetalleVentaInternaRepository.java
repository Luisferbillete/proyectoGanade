package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.DTO.Detalles_venta_interna.DetalleVentaInternaDTO;
import com.gandec.ganadecs.Entity.DetalleVentaInterna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleVentaInternaRepository extends JpaRepository<DetalleVentaInterna,Long> {
    @Query("SELECT new com.gandec.ganadecs.DTO.Detalles_venta_interna.DetalleVentaInternaDTO(dv.peso,dv.precio,dv.peso * dv.precio,dv.bovino.Numero,' ') FROM DetalleVentaInterna dv WHERE dv.ventaInterna.Id = :ventainternaId")
    List<DetalleVentaInternaDTO> findByVentaInternaId(@Param("ventainternaId") Long ventainternaId);



}
