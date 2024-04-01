package co.edu.uniquindio.controller.service;

import co.edu.uniquindio.mapping.dto.EmpleadoDto;

import java.util.List;

public interface IModelFactoryController {

    List<EmpleadoDto> obtenerEmpleados();
    boolean agregarEmpleado(EmpleadoDto empleadoDto);

    boolean eliminarEmpleado(String cedula);

    boolean actualizarEmpleado(String cedulaActual, EmpleadoDto empleadoDto);


}
