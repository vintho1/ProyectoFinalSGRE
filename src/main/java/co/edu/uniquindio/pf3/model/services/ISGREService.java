package co.edu.uniquindio.model.services;

import co.edu.uniquindio.exceptions.EmpleadoException;
import co.edu.uniquindio.model.Empleado;

import java.util.ArrayList;

public interface ISGREService {
    public Empleado crearEmpleado(String id, String nombre, String email) throws EmpleadoException;
    public Boolean eliminarEmpleado(String id)throws EmpleadoException;
    boolean actualizarEmpleado(String id, Empleado empleado) throws EmpleadoException;
    public boolean  verificarEmpleadoExistente(String id) throws EmpleadoException;
    public Empleado obtenerEmpleado(String id);
    public ArrayList<Empleado> obtenerEmpleados();

}
