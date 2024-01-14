package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.DTO.Crias.Cdto;
import com.gandec.ganadecs.DTO.Crias.CriasPorParto;
import com.gandec.ganadecs.DTO.Parto.Birthsdto;
import com.gandec.ganadecs.Entity.Crias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriasRepository extends JpaRepository<Crias, String> {



    @Query("SELECT COUNT(c) FROM Crias c JOIN c.partos p WHERE p.numero = :numeroParto ")
    int contarCriasPorNumeroParto(@Param("numeroParto") String numeroParto);



    @Query("SELECT new com.gandec.ganadecs.DTO.Crias.CriasPorParto" +
            "(c.numero,c.sexo,c.fecha_nacimiento,c.fecha_destete,c.raza,c.color,c.peso,c.estado) " +
            "FROM Crias c JOIN c.partos p WHERE p.numero = :numeroParto")
    List<CriasPorParto> obtenerCriasPorNumeroParto(@Param("numeroParto") String numeroParto);


    @Query("SELECT new com.gandec.ganadecs.DTO.Crias.Cdto(c.numero,c.fecha_destete,c.fecha_nacimiento) FROM Crias c JOIN c.partos p WHERE p.numero = :numeroParto")
    List<Cdto> obtenerCriasPorNumeroPartodto(@Param("numeroParto") String numeroParto);

    @Query("SELECT new com.gandec.ganadecs.DTO.Parto.Birthsdto(p.numero,p.nombre) FROM Partos p JOIN p.crias c WHERE c.numero = :numeroCria")
    Birthsdto findByNumeroCria(@Param("numeroCria") String numeroCria);
    @Query("SELECT new com.gandec.ganadecs.DTO.Crias.Cdto(c.numero,c.fecha_destete,c.fecha_nacimiento) FROM Crias c JOIN c.partos p WHERE p.numero = :numeroParto AND c.fecha_destete IS NULL")
    List<Cdto>verificarFechaNacimientoIgual(@Param("numeroParto") String  numeroParto) ;
}


