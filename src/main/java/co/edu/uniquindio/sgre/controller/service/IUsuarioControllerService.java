package co.edu.uniquindio.sgre.controller.service;

import co.edu.uniquindio.sgre.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.sgre.mapping.dto.UsuarioDto;

import java.util.List;

public interface IUsuarioControllerService {
    List<UsuarioDto> obtenerUsuario();

    boolean agregarUsuario(UsuarioDto usuarioDto);

    boolean eliminarUsuario(String id);

    boolean actualizarUsuario(String cedulaActual, UsuarioDto usuarioDto);

}
