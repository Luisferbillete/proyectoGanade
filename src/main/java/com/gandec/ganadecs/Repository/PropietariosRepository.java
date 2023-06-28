package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.Entity.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropietariosRepository extends JpaRepository<Propietario,Long> {
}
