package com.gandec.ganadecs.DTO.Hierro;

import com.gandec.ganadecs.DTO.EntityDTO;
import com.gandec.ganadecs.Entity.Propietario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
@NoArgsConstructor
@Data
public class HierroDto implements EntityDTO {
    private long id;
    private String figura;
    private String HierroUrl;
    private String HierroId;
    private String propietario;

    public HierroDto(long id, String figura, String hierroUrl, String hierroId, String propietario) {
        this.id = id;
        this.figura = figura;
        HierroUrl = hierroUrl;
        HierroId = hierroId;
        this.propietario = propietario;
    }
}
