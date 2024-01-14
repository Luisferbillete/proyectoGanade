package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.DTO.Bovinos.BovinesDTO;
import com.gandec.ganadecs.DTO.Bovinos.BovinosDTO;
import com.gandec.ganadecs.Entity.Bovino;
import com.gandec.ganadecs.Entity.Propietario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BovinoRepository extends JpaRepository<Bovino,String > {

@Query("select new com.gandec.ganadecs.DTO.Bovinos.BovinesDTO(b.Numero,b.Fecha_de_nacimiento,b.sexo) " +
        "from Bovino b where b.Numero = :numero")
    Optional<BovinesDTO> findBovinoByNumero(@Param("numero") String numero);


   @Query("SELECT new com.gandec.ganadecs.DTO.Bovinos.BovinosDTO" +
            "(b.Numero,CONCAT(p.nombres,' ',p.apellidos),b.sexo,b.color,b.raza,b.negocio,b.abaluo,b.kilos,b.preciokilo,b.Fecha_de_nacimiento,' ' ) " +
            "FROM Bovino b JOIN b.propietario p WHERE b.Numero NOT IN (SELECT bm.number FROM BovinosMuertos bm)"
    +"and b.Numero NOT IN (SELECT dv.bovino.Numero FROM Detalle_Venta dv)")
    List<BovinosDTO> BovinesGetAll();

    @Query("SELECT new com.gandec.ganadecs.DTO.Bovinos.BovinosDTO" +
                "(b.Numero,CONCAT(p.nombres,' ',p.apellidos),b.sexo,b.color,b.raza,b.negocio,b.abaluo,b.kilos,b.preciokilo,b.Fecha_de_nacimiento,' ' ) " +
                "FROM Bovino b JOIN b.propietario p WHERE b.Numero NOT IN (SELECT bm.number FROM BovinosMuertos bm)" +
                "and b.Numero NOT IN (SELECT dv.bovino.Numero FROM Detalle_Venta dv) and p.id = :propietarioId")
    List<BovinosDTO> BovinesGetallByPropietary(@Param("propietarioId") Long propietarioId);

    @Query("SELECT new com.gandec.ganadecs.DTO.Bovinos.BovinosDTO" +
            "(b.Numero,CONCAT(p.nombres,' ',p.apellidos),b.sexo,b.color,b.raza,b.negocio,b.abaluo,b.kilos,b.preciokilo,b.Fecha_de_nacimiento,' ' ) " +
            "FROM Bovino b JOIN b.propietario p WHERE b.Numero NOT IN (SELECT bm.number FROM BovinosMuertos bm)" +
            "and b.Numero NOT IN (SELECT dv.bovino.Numero FROM Detalle_Venta dv) and p.id = :propietarioId and b.sexo = :sexo")
    List<BovinosDTO> BovinesGetallByPropietaryAndSexo(@Param("propietarioId") Long propietarioId,
                                                      @Param("sexo") String sexo);
    @Query("SELECT new com.gandec.ganadecs.DTO.Bovinos.BovinosDTO" +
            "(b.Numero,CONCAT(p.nombres,' ',p.apellidos),b.sexo,b.color,b.raza,b.negocio,b.abaluo,b.kilos,b.preciokilo,b.Fecha_de_nacimiento,' ' ) " +
            "FROM Bovino b JOIN b.propietario p WHERE b.Numero NOT IN (SELECT bm.number FROM BovinosMuertos bm)" +
            "and b.Numero NOT IN (SELECT dv.bovino.Numero FROM Detalle_Venta dv) and b.sexo = :sex")
    List<BovinosDTO> BovinesGetallBySexo(@Param("sex") String sex);

    @Query("SELECT new com.gandec.ganadecs.DTO.Bovinos.BovinosDTO" +
            "(b.Numero,CONCAT(p.nombres,' ',p.apellidos),b.sexo,b.color,b.raza,b.negocio,b.abaluo,b.kilos,b.preciokilo,b.Fecha_de_nacimiento,' ' ) " +
            "FROM Bovino b JOIN b.propietario p WHERE b.Numero NOT IN (SELECT bm.number FROM BovinosMuertos bm)" +
            "and b.Numero NOT IN (SELECT dv.bovino.Numero FROM Detalle_Venta dv) and b.Numero = :numero")
    List<BovinosDTO> BovinesGetallByNumero(@Param("numero") String numero);

 @Modifying
 @Transactional
 @Query("UPDATE Bovino b SET b.propietario = :nuevoPropietario WHERE b.Numero = :numero")
 void UpdatePropietarioEnBovino(@Param("numero") String numero, @Param("nuevoPropietario") Propietario nuevoPropietario);

}
