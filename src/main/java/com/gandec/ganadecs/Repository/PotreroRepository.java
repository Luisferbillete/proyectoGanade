package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.Entity.Potrero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PotreroRepository  extends JpaRepository<Potrero,Long> {
}
