package com.gandec.ganadecs.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gandec.ganadecs.Entity.Propietario;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BovinoDTO implements EntityDTO {
    //@Column(unique = true)
    @NotEmpty(message = "Debe ingesar el numero")
    private String numero;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date Fecha_de_nacimiento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate Fecha_de_ingreso;
    private String sexo;
    private String color,raza;
    //private long propietario;
    @NotEmpty(message = "Selecione un tipo de negocio")
    private String negocio;
    private long abaluo;
    private int kilos;
    private long preciokilo;



}
