package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.Entity.Parto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartosRepository extends JpaRepository<Parto,Long> {
    @Query("SELECT COUNT(p) FROM Parto p JOIN p.bovino b WHERE b.Numero = :numeroId")
    Long countPartosByBovinoNumeroId(@Param("numeroId") String numeroId);
}
