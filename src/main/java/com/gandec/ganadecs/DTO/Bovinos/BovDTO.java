package com.gandec.ganadecs.DTO.Bovinos;

import com.gandec.ganadecs.DTO.EntityDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BovDTO implements EntityDTO {
    private String numero;
    private String propietario;
    private LocalDate Fecha_de_nacimiento;
    private String sexo;
    private String raza;
    private String negocio;
    private long abaluo;
    private int kilos;
    private long preciokilo;
    private String categoria;
    private String estado;







        }
