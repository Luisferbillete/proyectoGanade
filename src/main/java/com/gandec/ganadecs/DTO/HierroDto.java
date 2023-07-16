package com.gandec.ganadecs.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HierroDto implements EntityDTO{
    private long id;
    @NotEmpty
    private String figura;
}
