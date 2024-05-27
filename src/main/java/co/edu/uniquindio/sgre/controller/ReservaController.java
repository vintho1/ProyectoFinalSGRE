package co.edu.uniquindio.sgre.controller;

import co.edu.uniquindio.sgre.controller.service.IReservaControllerService;
import co.edu.uniquindio.sgre.mapping.dto.ReservaDto;

import java.util.List;

public class ReservaController implements IReservaControllerService {
    ModelFactoryController modelFactoryController;

    public ReservaController() {
        modelFactoryController = ModelFactoryController.getInstance();
    }

    @Override
    public List<ReservaDto> obtenerReservas() {
        return modelFactoryController.obtenerReservas();
    }

    @Override
    public List<ReservaDto> obtenerReservasUsuario(String cedula) {return modelFactoryController.obtenerReservasUsuario(cedula);
    }

    @Override
    public boolean agregarReserva(ReservaDto reservaDto) {
        return modelFactoryController.agregarReserva(reservaDto);
    }

    @Override
    public boolean eliminarReserva(String id) {
        return modelFactoryController.eliminarReserva(id);
    }

    @Override
    public boolean actualizarReserva(String idActual, ReservaDto reservaDto) {
        return modelFactoryController.actualizarReserva(idActual, reservaDto);
    }
}