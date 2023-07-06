package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.Entity.Bovino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BovinoRepository extends JpaRepository<Bovino,String > {
}
