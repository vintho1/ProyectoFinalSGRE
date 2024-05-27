package co.edu.uniquindio.sgre.mapping.dto;

import co.edu.uniquindio.sgre.model.Estado;
import co.edu.uniquindio.sgre.model.RolEmpleado;

public record
EmpleadoDto (
        String id,
        String nombre,
        String email,
        String rol,
        String contrasenia) {
}
