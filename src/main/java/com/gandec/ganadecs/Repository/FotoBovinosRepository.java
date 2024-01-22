package com.gandec.ganadecs.Repository;

import com.gandec.ganadecs.DTO.FotosBovinos.FotosBovinosDto;
import com.gandec.ganadecs.Entity.FotoBovinos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FotoBovinosRepository extends JpaRepository<FotoBovinos, Long> {
    @Query("SELECT new com.gandec.ganadecs.DTO.FotosBovinos.FotosBovinosDto(fb.id,fb.foto,fb.Fotourl,fb.idFoto,fb.bovino.Numero) FROM FotoBovinos fb ")
    List<FotosBovinosDto> findAllFotosBovinos();

    @Query("SELECT new com.gandec.ganadecs.DTO.FotosBovinos.FotosBovinosDto(fb.id,fb.foto,fb.Fotourl,fb.idFoto,fb.bovino.Numero) FROM FotoBovinos fb JOIN fb.bovino b WHERE b.Numero = :numeroBovino")
    List<FotosBovinosDto> findFotosBovinosByNumeroBovino(@Param("numeroBovino") String numeroBovino);

}
