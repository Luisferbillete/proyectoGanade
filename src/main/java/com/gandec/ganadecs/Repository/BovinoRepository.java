package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.DTO.Bovinos.*;
import com.gandec.ganadecs.Entity.Bovino;
import com.gandec.ganadecs.Entity.Propietario;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface BovinoRepository extends JpaRepository<Bovino,String > {
    @Query("SELECT b FROM Bovino b WHERE b.Numero = :numero")
    Optional<Bovino> findByNumero(@Param("numero") String numero);


    @Query("SELECT new com.gandec.ganadecs.DTO.Bovinos.BovinosGetAll" +
            "(b.Numero, CONCAT(p.nombres, ' ', p.apellidos), b.sexo, b.color, b.raza, b.negocio, b.abaluo, b.kilos, b.preciokilo, b.fecha_de_nacimiento, " +
            "(SELECT pt.nombre FROM MovimientoBovino mb JOIN mb.potrero pt WHERE mb.bovino.Numero = b.Numero ORDER BY mb.fecha_de_ingreso DESC LIMIT 1),' '" + ") " +
            "FROM Bovino b " +
            "JOIN b.propietario p " +
            "WHERE b.Numero NOT IN (SELECT bm.number FROM BovinosMuertos bm) " +
            "AND b.Numero NOT IN (SELECT dv.bovino.Numero FROM Detalle_Venta dv)")
    Page<BovinosGetAll> BovinesGetAll2(Pageable pageable);

    @Query("SELECT new com.gandec.ganadecs.DTO.Bovinos.BovinosPorPotrero" +
            "(b.Numero, pt.nombre) " +
            "FROM Bovino b " +
            "JOIN MovimientoBovino mb ON mb.bovino.Numero = b.Numero " +
            "JOIN mb.potrero pt " +
            "WHERE b.Numero NOT IN (SELECT bm.number FROM BovinosMuertos bm) " +
            "AND b.Numero NOT IN (SELECT dv.bovino.Numero FROM Detalle_Venta dv) " +
            "AND pt.id = :IdPotrero " +
            "AND mb.fecha_de_salida IS NULL " +  // Filtro adicional para la fecha_de_salida
            "ORDER BY mb.fecha_de_ingreso DESC")
    Page<BovinosPorPotrero> BovinosPorPotrero(@Param("IdPotrero") Long IdPotrero, Pageable pageable);


    @Query("select new com.gandec.ganadecs.DTO.Bovinos.BovinesDTO(b.Numero,b.fecha_de_nacimiento,b.sexo) " +
        "from Bovino b where b.Numero = :numero")
    Optional<BovinesDTO> findBovinoByNumero(@Param("numero") String numero);
    @Query("select new com.gandec.ganadecs.DTO.Bovinos.BovinosFindByNumero(b.Numero,b.propietario.id,b.sexo,b.color,b.raza,b.negocio,b.abaluo,b.kilos,b.preciokilo,b.fecha_de_nacimiento,b.Fecha_de_ingreso) " +
            "from Bovino b where b.Numero = :numero")
    Optional<BovinosFindByNumero> findBovinoByNumeroUp(@Param("numero") String numero);

   @Query("SELECT new com.gandec.ganadecs.DTO.Bovinos.BovinosDTO" +
            "(b.Numero,CONCAT(p.nombres,' ',p.apellidos),b.sexo,b.color,b.raza,b.negocio,b.abaluo,b.kilos,b.preciokilo,b.fecha_de_nacimiento,' ' ) " +
            "FROM Bovino b JOIN b.propietario p WHERE b.Numero NOT IN (SELECT bm.number FROM BovinosMuertos bm)"
    +"and b.Numero NOT IN (SELECT dv.bovino.Numero FROM Detalle_Venta dv)")
   Page<BovinosDTO> BovinesGetAll(Pageable pageable);

    @Query("SELECT new com.gandec.ganadecs.DTO.Bovinos.BovinosDTO" +
                "(b.Numero,CONCAT(p.nombres,' ',p.apellidos),b.sexo,b.color,b.raza,b.negocio,b.abaluo,b.kilos,b.preciokilo,b.fecha_de_nacimiento,' ' ) " +
                "FROM Bovino b JOIN b.propietario p WHERE b.Numero NOT IN (SELECT bm.number FROM BovinosMuertos bm)" +
                "and b.Numero NOT IN (SELECT dv.bovino.Numero FROM Detalle_Venta dv) and p.id = :propietarioId")
    List<BovinosDTO> BovinesGetallByPropietary(@Param("propietarioId") Long propietarioId);

    @Query("SELECT new com.gandec.ganadecs.DTO.Bovinos.BovinosDTO" +
            "(b.Numero,CONCAT(p.nombres,' ',p.apellidos),b.sexo,b.color,b.raza,b.negocio,b.abaluo,b.kilos,b.preciokilo,b.fecha_de_nacimiento,' ' ) " +
            "FROM Bovino b JOIN b.propietario p WHERE b.Numero NOT IN (SELECT bm.number FROM BovinosMuertos bm)" +
            "and b.Numero NOT IN (SELECT dv.bovino.Numero FROM Detalle_Venta dv) and p.id = :propietarioId and b.sexo = :sexo")
    List<BovinosDTO> BovinesGetallByPropietaryAndSexo(@Param("propietarioId") Long propietarioId,
                                                      @Param("sexo") String sexo);
    @Query("SELECT new com.gandec.ganadecs.DTO.Bovinos.BovinosDTO" +
            "(b.Numero,CONCAT(p.nombres,' ',p.apellidos),b.sexo,b.color,b.raza,b.negocio,b.abaluo,b.kilos,b.preciokilo,b.fecha_de_nacimiento,' ' ) " +
            "FROM Bovino b JOIN b.propietario p WHERE b.Numero NOT IN (SELECT bm.number FROM BovinosMuertos bm)" +
            "and b.Numero NOT IN (SELECT dv.bovino.Numero FROM Detalle_Venta dv) and b.sexo = :sex")
    List<BovinosDTO> BovinesGetallBySexo(@Param("sex") String sex);

    @Query("SELECT new com.gandec.ganadecs.DTO.Bovinos.BovinosDTO" +
            "(b.Numero,CONCAT(p.nombres,' ',p.apellidos),b.sexo,b.color,b.raza,b.negocio,b.abaluo,b.kilos,b.preciokilo,b.fecha_de_nacimiento,' ' ) " +
            "FROM Bovino b JOIN b.propietario p WHERE b.Numero NOT IN (SELECT bm.number FROM BovinosMuertos bm)" +
            "and b.Numero NOT IN (SELECT dv.bovino.Numero FROM Detalle_Venta dv) and b.Numero = :numero")
    List<BovinosDTO> BovinesGetallByNumero(@Param("numero") String numero);

 @Modifying
 @Transactional
 @Query("UPDATE Bovino b SET b.propietario = :nuevoPropietario WHERE b.Numero = :numero")
 void UpdatePropietarioEnBovino(@Param("numero") String numero, @Param("nuevoPropietario") Propietario nuevoPropietario);

}
