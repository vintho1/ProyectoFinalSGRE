package co.edu.uniquindio.sgre.controller;

import co.edu.uniquindio.sgre.controller.service.IUsuarioControllerService;
import co.edu.uniquindio.sgre.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.sgre.mapping.dto.UsuarioDto;

import java.util.List;

public class UsuarioController implements IUsuarioControllerService {
    ModelFactoryController modelFactoryController;

    public UsuarioController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    public List<UsuarioDto> obtenerUsuario() {
        return modelFactoryController.obtenerUsuario();
    }

    @Override
    public boolean agregarUsuario(UsuarioDto usuarioDto) {
        return modelFactoryController.agregarUsuario(usuarioDto);
    }

    @Override
    public boolean eliminarUsuario(String cedula) {
        return modelFactoryController.eliminarUsuario(cedula);
    }

    @Override
    public boolean actualizarUsuario(String cedulaActual, UsuarioDto usuarioDto) {
        return modelFactoryController.actualizarUsuario(cedulaActual, usuarioDto);
    }
}
