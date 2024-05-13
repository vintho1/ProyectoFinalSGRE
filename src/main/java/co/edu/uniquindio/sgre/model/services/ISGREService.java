package co.edu.uniquindio.sgre.model.services;

import co.edu.uniquindio.sgre.exceptions.EmpleadoException;
import co.edu.uniquindio.sgre.exceptions.UsuarioException;
import co.edu.uniquindio.sgre.model.Empleado;
import co.edu.uniquindio.sgre.model.Usuario;

import java.util.ArrayList;

public interface ISGREService {
    public Empleado crearEmpleado(String id, String nombre, String email) throws EmpleadoException;
    public Boolean eliminarEmpleado(String id)throws EmpleadoException;
    boolean actualizarEmpleado(String id, Empleado empleado) throws EmpleadoException;
    public boolean  verificarEmpleadoExistente(String id) throws EmpleadoException;
    public Empleado obtenerEmpleado(String id);
    public ArrayList<Empleado> obtenerEmpleados();

    ////////

    public Usuario crearUsuario(String id, String nombre, String email, String usuario, String contrasenia) throws UsuarioException;
    public void agregarUsuario(Usuario nuevoUsuario) throws UsuarioException;
    public boolean verificarUsuarioExistente(String id) throws UsuarioException;
    public boolean usuarioExiste(String id);
    public boolean actualizarUsuario(String id, Usuario usuario) throws UsuarioException;
    public Boolean eliminarUsuario(String id) throws UsuarioException;
    public Usuario obtenerUsuario(String cedula);
    public ArrayList<Usuario> obtenerUsuario();






}
