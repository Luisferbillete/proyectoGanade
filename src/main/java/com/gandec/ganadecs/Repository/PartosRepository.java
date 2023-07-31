package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.DTO.PartosDTO;
import com.gandec.ganadecs.Entity.Parto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartosRepository extends JpaRepository<Parto,Long> {
    @Query("SELECT COUNT(p) FROM Parto p JOIN p.bovino b WHERE b.Numero = :numeroId")
    Long countPartosByBovinoNumeroId(@Param("numeroId") String numeroId);

    @Query("SELECT new com.gandec.ganadecs.DTO.PartosDTO(p.fecha_de_parto,p.nombre,b.Numero)  FROM Parto p JOIN p.bovino b WHERE p.fecha_de_destete IS NULL AND b.Numero = :numeroId")
    List<PartosDTO> findPartosByFechaNullAndBovinoNumeroId(@Param("numeroId") String numeroId);
}
