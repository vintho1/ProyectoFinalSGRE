package co.edu.uniquindio.sgre.controller.service;

import co.edu.uniquindio.sgre.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.sgre.mapping.dto.EventoDto;
import co.edu.uniquindio.sgre.mapping.dto.ReservaDto;
import co.edu.uniquindio.sgre.mapping.dto.UsuarioDto;

import java.util.List;

public interface IModelFactoryController {

    List<EmpleadoDto> obtenerEmpleados();
    boolean agregarEmpleado(EmpleadoDto empleadoDto);

    boolean eliminarEmpleado(String cedula);

    boolean actualizarEmpleado(String cedulaActual, EmpleadoDto empleadoDto);


    ///////////

    boolean agregarUsuario(UsuarioDto empleadoDto);

    boolean eliminarUsuario(String cedula);

    boolean actualizarUsuario(String cedulaActual, UsuarioDto empleadoDto);

    List<ReservaDto> obtenerReservasUsuario(String cedula);

    List<UsuarioDto> obtenerUsuario();


    /////////

    List<EventoDto> obtenerEventos();

    boolean agregarEvento(EventoDto eventoDto);

    boolean eliminarEvento(String id);

    boolean actualizarEvento(String idActual, EventoDto eventoDto);

    ////////////

    List<ReservaDto> obtenerReservas();

    boolean agregarReserva(ReservaDto reservaDto);

    boolean eliminarReserva(String id);

    boolean actualizarReserva(String idActual, ReservaDto reservaDto);
}
