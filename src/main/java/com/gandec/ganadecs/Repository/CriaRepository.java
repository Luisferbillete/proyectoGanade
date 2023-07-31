package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.Entity.Cria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriaRepository extends JpaRepository<Cria,String> {
}
