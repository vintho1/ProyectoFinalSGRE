package co.edu.uniquindio.sgre.model.services;

import co.edu.uniquindio.sgre.exceptions.EmpleadoException;
import co.edu.uniquindio.sgre.model.Empleado;

import java.util.ArrayList;

public interface ISGREService {
    public Empleado crearEmpleado(String id, String nombre, String email) throws EmpleadoException;
    public Boolean eliminarEmpleado(String id)throws EmpleadoException;
    boolean actualizarEmpleado(String id, Empleado empleado) throws EmpleadoException;
    public boolean  verificarEmpleadoExistente(String id) throws EmpleadoException;
    public Empleado obtenerEmpleado(String id);
    public ArrayList<Empleado> obtenerEmpleados();

}
