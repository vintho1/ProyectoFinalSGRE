package co.edu.uniquindio.sgre.mapping.dto;

import co.edu.uniquindio.sgre.model.Estado;
import co.edu.uniquindio.sgre.model.Evento;

import java.time.LocalDate;

public record ReservaDto (
        String id,
        String capacidad,
        String usuarioId,
        Evento eventoId,
        String fecha,
        Estado estado) {

        }