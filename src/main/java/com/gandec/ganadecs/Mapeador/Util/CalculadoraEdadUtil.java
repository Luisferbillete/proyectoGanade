package com.gandec.ganadecs.Mapeador.Util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
@Component
public class CalculadoraEdadUtil {
    public String calcularCategoria(LocalDate fechaNacimiento, String sexo) {

        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(fechaNacimiento, fechaActual);
        int edadEnMeses = periodo.getYears() * 12 + periodo.getMonths();

        if (sexo.equalsIgnoreCase("macho")) {
            if (edadEnMeses <= 12) {
                return "Ternero";
            } else if (edadEnMeses <= 24) {
                return "Novillo";
            } else {
                return "Otra categoría para machos";
            }
        } else if (sexo.equalsIgnoreCase("hembra")) {
            if (edadEnMeses <= 12) {
                return "Ternera";
            } else if (edadEnMeses <= 24) {
                return "Novilla";
            } else {
                return "Otra categoría para hembras";
            }
        } else {
            return "Desconocido";
        }

    }
}
