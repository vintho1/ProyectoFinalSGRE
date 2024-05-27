package co.edu.uniquindio.sgre.controller.service;

import co.edu.uniquindio.sgre.mapping.dto.ReservaDto;

import java.util.List;

public interface IReservaControllerService {
    List<ReservaDto> obtenerReservas();

    List<ReservaDto> obtenerReservasUsuario(String cedula);

    boolean agregarReserva(ReservaDto reservaDto);

    boolean eliminarReserva(String id);

    boolean actualizarReserva(String idActual, ReservaDto reservaDto);
}
