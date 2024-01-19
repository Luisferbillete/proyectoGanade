package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.DTO.Parto.PartosFindPropietarioDTO;
import com.gandec.ganadecs.DTO.Parto.PartosPorPropietarioYCriasYSexo;
import com.gandec.ganadecs.DTO.Parto.PartosPropietariosDTO;
import com.gandec.ganadecs.Entity.Partos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartoRepository extends JpaRepository<Partos, String> {



@Query("SELECT new com.gandec.ganadecs.DTO.Parto.PartosPropietariosDTO" +
        "(b.propietario.id,b.propietario.nombres," +
        "b.propietario.apellidos,p.numero,p.nombre,c.fecha_nacimiento) " +
        "FROM Partos p JOIN p.bovino b JOIN p.crias c WHERE c.fecha_destete IS NULL")
    List<PartosPropietariosDTO>findPartosAndPropietarioWithNullDestete();

    @Query("SELECT new com.gandec.ganadecs.DTO.Parto.PartosFindPropietarioDTO" +
            "(p.numero,p.nombre,c.fecha_nacimiento,c.numero,c.sexo,c.raza,c.estado) " +
            "FROM Partos p JOIN p.bovino b JOIN p.crias c WHERE c.fecha_destete IS NULL AND b.propietario.id = :propietarioId")
    List<PartosFindPropietarioDTO> findPartosAndPropietarioDTOWithNullDesteteAndPropietarioId
            (@Param("propietarioId") Long propietarioId);


    @Query("SELECT new com.gandec.ganadecs.DTO.Parto.PartosPorPropietarioYCriasYSexo" +
            "(CONCAT(b.propietario.nombres,' ',b.propietario.apellidos),p.numero,p.nombre,c.fecha_nacimiento,c.numero,c.sexo,c.raza,c.estado)" +

            "FROM Partos p JOIN p.bovino b JOIN p.crias c WHERE c.fecha_destete IS NULL and c.sexo = :sexoCria")
    List<PartosPorPropietarioYCriasYSexo>FinAllPartosPorSexoCria(@Param("sexoCria") String sexoCria);

    @Query("SELECT new com.gandec.ganadecs.DTO.Parto.PartosPorPropietarioYCriasYSexo" +
            "(CONCAT(b.propietario.nombres,' ',b.propietario.apellidos),p.numero,p.nombre,c.fecha_nacimiento,c.numero,c.sexo,c.raza,c.estado)" +

            "FROM Partos p JOIN p.bovino b JOIN p.crias c WHERE c.fecha_destete IS NULL and c.sexo = :sexoCria " +
            "and b.propietario.id = :propietarioId")
    List<PartosPorPropietarioYCriasYSexo>FinAllPartosPorSexoCriaYPropietary
            (@Param("sexoCria") String sexoCria,
             @Param("propietarioId") Long propietarioId );


}