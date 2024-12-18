package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.DTO.Propietary.PropietaryGetAll;
import com.gandec.ganadecs.DTO.Propietary.PropietaryComboDto;
import com.gandec.ganadecs.Entity.ERole;
import com.gandec.ganadecs.Entity.Propietario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PropietariosRepository extends JpaRepository<Propietario,Long> {
    Optional<Propietario> findByEmail(String email);

    Optional<Propietario> findByUsername(String username);
    @Query("SELECT new com.gandec.ganadecs.DTO.Propietary.PropietaryComboDto" +
            "(p.id, CONCAT(p.nombres, ' ', p.apellidos)) " +
            "FROM Propietario p WHERE LOWER(p.nombres) = LOWER(:nombres) AND LOWER(p.apellidos) = LOWER(:apellidos)")
    PropietaryComboDto findPropietaryComboDtoByNombresAndApellidos
            (@Param("nombres") String nombres, @Param("apellidos") String apellidos);


//    @Query("SELECT new com.gandec.ganadecs.DTO.Propietary.PropietaryComboDto" +
//            "(p.id, CONCAT(p.nombres, ' ', p.apellidos)) " +
//            "FROM Propietario p WHERE p.nombres = :nombres AND p.apellidos = :apellidos")
//    PropietaryComboDto findPropietaryComboDtoByNombresAndApellidos
//            (@Param("nombres") String nombres, @Param("apellidos") String apellidos);

//@Query("SELECT new com.gandec.ganadecs.DTO.Propietary.PropietaryGetAll" +
//        "(p.id, p.nombres, p.apellidos, p.direccion, p.telefonos, p.email) " +
//        "FROM Propietario p " +
//        "JOIN p.roles r " +
//        "WHERE r.name = 'PROPIETARIO'")
//Page<PropietaryGetAll> findAllPropietariesWithRole(Pageable pageable);
    @Query("SELECT new com.gandec.ganadecs.DTO.Propietary.PropietaryGetAll" +
            "(p.id, p.nombres, p.apellidos, p.direccion, p.telefonos, p.email) " +
            "FROM Propietario p " +
            "JOIN p.roles r " +
            "WHERE r.name = :roleName")
    Page<PropietaryGetAll> findAllUserWithRole(@Param("roleName") ERole roleName, Pageable pageable);

    @Query("SELECT new com.gandec.ganadecs.DTO.Propietary.PropietaryComboDto" +
            "(p.id, CONCAT(p.nombres, ' ', p.apellidos)) " +
            "FROM Propietario p " +
            "JOIN p.roles r " +
            "WHERE r.name = 'PROPIETARIO'")
List<PropietaryComboDto> getPropietary();

}

