package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.DTO.MovimientoBovinoDTO;
import com.gandec.ganadecs.Entity.Bovino;
import com.gandec.ganadecs.Entity.MovimientoBovino;
import com.gandec.ganadecs.Entity.Potrero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovimientoBovinoRepository extends JpaRepository<MovimientoBovino,Long> {
    @Query("select new com.gandec.ganadecs.DTO.MovimientoBovinoDTO" +
            "(b.Numero,m.fecha_de_ingreso,m.fecha_de_salida,p.id) " +
            "from MovimientoBovino m join m.bovino b join m.potrero p where p.id=:potrero " +
            "and m.fecha_de_salida is null " )

    List<MovimientoBovinoDTO> findMovimientoBovinoByPotrero(@Param("potrero")long potrero);
    @Query("select new com.gandec.ganadecs.DTO.MovimientoBovinoDTO(b.Numero,m.fecha_de_ingreso,m.fecha_de_salida) from MovimientoBovino m join m.bovino b where b.Numero=:numero and m.fecha_de_salida is null ")
    List<MovimientoBovinoDTO> findMovimientoBovinoByBovino(@Param("numero") String numero);

    @Query("select new com.gandec.ganadecs.DTO.MovimientoBovinoDTO(b.Numero,m.fecha_de_ingreso,m.fecha_de_salida) from MovimientoBovino m join m.bovino b where m.potrero=:potrero and m.fecha_de_salida  is null ")

     List<MovimientoBovinoDTO> findMovimientoBovinoByPotreroAndFecha_de_salidaIsNull(Potrero potrero);
    @Query("select new com.gandec.ganadecs.DTO.MovimientoBovinoDTO(b.Numero,m.fecha_de_ingreso,m.fecha_de_salida) " +
            "from MovimientoBovino m join m.bovino b join m.potrero p where p.id=:potrero " +
            "and m.fecha_de_salida is null " )

    List<MovimientoBovinoDTO> todo(@Param("potrero")long potrero);
        @Modifying
        @Transactional
        @Query("update MovimientoBovino m set  m.fecha_de_salida =:fechaSalida  where m.potrero=:potrero and m.fecha_de_salida is null " )

          int UpdateMovimientobovinosFechaSalida(@Param("potrero") Potrero potrero,@Param("fechaSalida" )LocalDate fechaSalida);
        @Modifying
        @Transactional
        @Query(value = "UPDATE moviento_de_reses m " +
                "JOIN potreros p ON m.potrero_id = p.id " +
                "SET m.fecha_de_salida = :newFechaDeSalida " +
                "WHERE m.fecha_de_salida IS NULL AND p.id = :potreroId", nativeQuery = true)
        int updateFechaDeSalida(@Param("newFechaDeSalida") java.sql.Date newFechaDeSalida, @Param("potreroId") long potreroId);}
