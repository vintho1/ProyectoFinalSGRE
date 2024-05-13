package co.edu.uniquindio.sgre.mapping.dto;

public record EventoDto (
        String id,
        String nombre,
        String descripcion,
        String fecha,
        String capMax,
        String empleadoAsignadoId) {

        }