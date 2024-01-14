package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.DTO.Detalles_ventas.DetallesVentasDTO;
import com.gandec.ganadecs.Entity.Bovino;
import com.gandec.ganadecs.Entity.Detalle_Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<Detalle_Venta,Long> {
    List<Detalle_Venta> findByBovino(Bovino bovino);
    @Query("SELECT new com.gandec.ganadecs.DTO.Detalles_ventas.DetallesVentasDTO(dv.peso,dv.precio,dv.peso * dv.precio,dv.bovino.Numero,' ') FROM Detalle_Venta dv WHERE dv.venta.Id = :ventaId")
    List<DetallesVentasDTO> findByVentaId(@Param("ventaId") Long ventaId);
}
