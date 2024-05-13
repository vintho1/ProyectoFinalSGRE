package co.edu.uniquindio.sgre.controller.service;

import co.edu.uniquindio.sgre.mapping.dto.EventoDto;

import java.util.List;

public interface IEventoControllerService {

    List<EventoDto> obtenerEventos();

    boolean agregarEvento(EventoDto eventoDto);

    boolean eliminarEvento(String id);

    boolean actualizarEvento(String idActual, EventoDto eventoDto);
}