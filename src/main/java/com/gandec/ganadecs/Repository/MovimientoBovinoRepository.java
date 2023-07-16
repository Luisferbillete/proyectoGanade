package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.Entity.MovimientoBovino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoBovinoRepository extends JpaRepository<MovimientoBovino,Long> {
}
