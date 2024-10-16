package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.DTO.MovimientoBovinoDTO;
import com.gandec.ganadecs.DTO.MovimientosDTO;
import com.gandec.ganadecs.Entity.MovimientoBovino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MovimientoBovinoRepository extends JpaRepository<MovimientoBovino,Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE movimiento_bovino m " +
            "JOIN potrero p ON m.potrero_id = p.id " +
            "SET m.fecha_de_salida = :newFechaDeSalida " +
            "WHERE m.fecha_de_salida IS NULL " +
            "AND p.id = :potreroId " +
            "AND m.bovino_numero IN (:bovinoIds)", nativeQuery = true)
    int updateFechaDeSalidaTraslado(@Param("newFechaDeSalida") java.sql.Date newFechaDeSalida,
                            @Param("potreroId") long potreroId,
                            @Param("bovinoIds") List<String> bovinoIds);

    @Query("select new com.gandec.ganadecs.DTO.MovimientoBovinoDTO" +
            "(b.Numero,m.fecha_de_ingreso,m.fecha_de_salida,p.id) " +
            "from MovimientoBovino m join m.bovino b join m.potrero p where p.id=:potrero " +
            "and m.fecha_de_salida is null " )

    List<MovimientoBovinoDTO> findMovimientoBovinoByPotrero(@Param("potrero")long potrero);
    @Query("SELECT NEW com.gandec.ganadecs.DTO.MovimientosDTO" +
            "(b.Numero, m.fecha_de_ingreso, p.nombre) " +
            "FROM MovimientoBovino m JOIN m.bovino b JOIN m.potrero p " +
            "WHERE  m.fecha_de_salida IS NULL")
    List<MovimientosDTO> getAllBovinespaddock();
    @Query("select new com.gandec.ganadecs.DTO.MovimientoBovinoDTO(b.Numero," +
            "m.fecha_de_ingreso,m.fecha_de_salida) " +
            "from MovimientoBovino m join m.bovino b where b.Numero=:numero and m.fecha_de_salida is null ")
    List<MovimientoBovinoDTO> findMovimientoBovinoByBovino(@Param("numero") String numero);

    @Query("select new com.gandec.ganadecs.DTO.MovimientoBovinoDTO(b.Numero,m.fecha_de_ingreso,m.fecha_de_salida) " +
            "from MovimientoBovino m join m.bovino b join m.potrero p where p.id=:potrero " +
            "and m.fecha_de_salida is null " )

    List<MovimientoBovinoDTO> todo(@Param("potrero")long potrero);

        @Modifying
        @Transactional
        @Query(value = "UPDATE moviento_de_reses m " +
                "JOIN potreros p ON m.potrero_id = p.id " +
                "SET m.fecha_de_salida = :newFechaDeSalida " +
                "WHERE m.fecha_de_salida IS NULL AND p.id = :potreroId", nativeQuery = true)
        int updateFechaDeSalida(@Param("newFechaDeSalida") java.sql.Date newFechaDeSalida, @Param("potreroId") long potreroId);


    @Modifying
    @Transactional
    @Query(value = "UPDATE moviento_de_reses m " +
            "JOIN potreros p ON m.potrero_id = p.id " +
            "JOIN bovinos b ON m.bovino_numero = b.numero " +
            "SET m.fecha_de_salida = :newFechaDeSalida " +
            "WHERE m.fecha_de_salida IS NULL and b.numero= :numeroId", nativeQuery = true)
    int updateMovimientoBovinoByBovino(@Param("newFechaDeSalida") java.sql.Date newFechaDeSalida,
                                       @Param("numeroId") String numero);
}