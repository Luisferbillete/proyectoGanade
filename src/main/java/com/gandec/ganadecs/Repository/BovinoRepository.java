package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.Entity.Bovino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BovinoRepository extends JpaRepository<Bovino,String > {

}
