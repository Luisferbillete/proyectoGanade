package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.DTO.PropietaryComboDto;
import com.gandec.ganadecs.Entity.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PropietariosRepository extends JpaRepository<Propietario,Long> {

    Optional<Propietario> findByUsername (String username);
    @Query("SELECT new com.gandec.ganadecs.DTO.PropietaryComboDto" +
            "(p.id, CONCAT(p.nombres, ' ', p.apellidos)) " +
            "FROM Propietario p WHERE p.nombres = :nombres AND p.apellidos = :apellidos")
    PropietaryComboDto findPropietaryComboDtoByNombresAndApellidos
            (@Param("nombres") String nombres, @Param("apellidos") String apellidos);
}

