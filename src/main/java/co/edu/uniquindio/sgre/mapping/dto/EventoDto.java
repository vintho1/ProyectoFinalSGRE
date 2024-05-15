package co.edu.uniquindio.sgre.mapping.dto;

import java.time.LocalDate;

public record EventoDto (
        String id,
        String nombre,
        String descripcion,
        LocalDate fecha,
        String capMax,
        String empleadoAsignadoId) {

        }