package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.DTO.PartoDTO;
import com.gandec.ganadecs.DTO.PartosDTO;
import com.gandec.ganadecs.Entity.Parto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PartosRepository extends JpaRepository<Parto,Long> {
    @Query("SELECT COUNT(p) FROM Parto p JOIN p.bovino b WHERE b.Numero = :numeroId")
    Long countPartosByBovinoNumeroId(@Param("numeroId") String numeroId);

    @Query("SELECT NEW com.gandec.ganadecs.DTO.PartoDTO(p.fecha_de_parto,p.fecha_de_destete,p.nombre,p.bovino) " +
            "FROM Parto p WHERE p.bovino.Numero = :numeroBovino AND p.fecha_de_destete IS NULL")
    List<PartoDTO> findByNumeroBovinoAndFechaDesteteIsNull(@Param("numeroBovino") String numeroBovino);

    @Query("SELECT new com.gandec.ganadecs.DTO.PartosDTO(p.fecha_de_parto,p.nombre,b.Numero)  " +
            "FROM Parto p JOIN p.bovino b WHERE p.fecha_de_destete IS NULL AND b.Numero = :numeroId")
    List<PartosDTO> findPartosByFechaNullAndBovinoNumeroId(@Param("numeroId") String numeroId);

    @Transactional
    @Modifying
    @Query("UPDATE Parto p SET p.fecha_de_destete = :nuevaFechaDestete WHERE p.bovino.Numero = :numeroBovino")
    int actualizarFechaDestete(@Param("numeroBovino") String numeroBovino, @Param("nuevaFechaDestete")
    LocalDate nuevaFechaDestete);
}

