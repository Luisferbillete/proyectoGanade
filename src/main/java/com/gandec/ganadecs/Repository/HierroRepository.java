package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.Entity.Hierro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HierroRepository extends JpaRepository<Hierro,Long> {
}
