package co.edu.uniquindio.parfin.controller.service;

import co.edu.uniquindio.parfin.mapping.dto.EmpleadoDto;

import java.util.List;

public interface IModelFactoryController {

    List<EmpleadoDto> obtenerEmpleados();
    boolean agregarEmpleado(EmpleadoDto empleadoDto);

    boolean eliminarEmpleado(String cedula);

    boolean actualizarEmpleado(String cedulaActual, EmpleadoDto empleadoDto);


}
