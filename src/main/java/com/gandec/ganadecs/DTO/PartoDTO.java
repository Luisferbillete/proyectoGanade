package com.gandec.ganadecs.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gandec.ganadecs.Entity.Bovino;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor


public class PartoDTO implements EntityDTO {

    private long id;
    @NotNull(message = "La fecha de parto no puede estar vacia")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha_de_parto;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha_de_destete;
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String nombre;
    private Bovino bovino;
}
