package co.edu.uniquindio.sgre.controller.service;

import co.edu.uniquindio.sgre.mapping.dto.EmpleadoDto;

import java.util.List;

public interface IEmpladoControllerService {

    List<EmpleadoDto> obtenerEmpleados();

    boolean agregarEmpleado(EmpleadoDto empleadoDto);

    boolean eliminarEmpleado(String id);

    boolean actualizarEmpleado(String cedulaActual, EmpleadoDto empleadoDto);

}
