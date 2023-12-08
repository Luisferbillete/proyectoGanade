package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.DTO.PropietarioDTO;
import com.gandec.ganadecs.Entity.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropietariosRepository extends JpaRepository<Propietario,Long> {}

