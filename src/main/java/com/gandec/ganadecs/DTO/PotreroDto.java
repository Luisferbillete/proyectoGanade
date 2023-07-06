package com.gandec.ganadecs.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PotreroDto implements EntityDTO{
    private long id;
    @NotEmpty
    private String nombre;
    @NotNull
    private long Hectareas;

}
