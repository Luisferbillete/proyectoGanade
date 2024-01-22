package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.DTO.Hierro.HierroDto;
import com.gandec.ganadecs.Entity.Hierro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HierroRepository extends JpaRepository<Hierro,Long> {
    @Query("SELECT new com.gandec.ganadecs.DTO.Hierro.HierroDto" +
            "(h.id,h.figura,h.HierroUrl,h.HierroId,CONCAT( h.propietario.nombres,' ',h.propietario.apellidos)) FROM Hierro h ")
    List<HierroDto> findAllHierros();

    @Query("SELECT h FROM Hierro h WHERE h.propietario.id = :propietarioId")
    List<Hierro> findHierrosByPropietario(@Param("propietarioId") Long propietarioId);
}
