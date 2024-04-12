package co.edu.uniquindio.sgre.model;

import co.edu.uniquindio.sgre.exceptions.EmpleadoException;
import co.edu.uniquindio.sgre.model.services.ISGREService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class SGRE implements ISGREService, Serializable {
    private static final long serialVersionUID = 1L;
    ArrayList<Usuario> listaUsuarios = new ArrayList();
    ArrayList<Empleado> listaEmpleados = new ArrayList();
    ArrayList<Evento> listaEventos = new ArrayList();
    ArrayList<Reserva> listaReservas = new ArrayList();

    public SGRE() {
    }

    public Empleado crearEmpleado(String id, String nombre, String email) throws EmpleadoException {
        Empleado nuevoEmpleado = null;
        boolean empleadoExiste = this.verificarEmpleadoExistente(id);
        if (empleadoExiste) {
            throw new EmpleadoException("El empleado con cedula: " + id + " ya existe");
        } else {
            nuevoEmpleado = new Empleado();
            nuevoEmpleado.setNombre(nombre);
            nuevoEmpleado.setId(id);
            nuevoEmpleado.setEmail(email);
            this.getListaEmpleados().add(nuevoEmpleado);
            return nuevoEmpleado;
        }
    }

    public void agregarEmpleado(Empleado nuevoEmpleado) throws EmpleadoException {
        this.getListaEmpleados().add(nuevoEmpleado);
    }

    public boolean verificarEmpleadoExistente(String id) throws EmpleadoException {
        if (this.empleadoExiste(id)) {
            throw new EmpleadoException("El empleado con cedula: " + id + " ya existe");
        } else {
            return false;
        }
    }

    public boolean empleadoExiste(String id) {
        boolean empleadoEncontrado = false;
        Iterator var3 = this.getListaEmpleados().iterator();

        while(var3.hasNext()) {
            Empleado empleado = (Empleado)var3.next();
            if (empleado.getId().equalsIgnoreCase(id)) {
                empleadoEncontrado = true;
                break;
            }
        }

        return empleadoEncontrado;
    }

    public boolean actualizarEmpleado(String id, Empleado empleado) throws EmpleadoException {
        Empleado empleadoActual = this.obtenerEmpleado(id);
        if (empleadoActual == null) {
            throw new EmpleadoException("El empleado a actualizar no existe");
        } else {
            empleadoActual.setId(empleado.getId());
            empleadoActual.setNombre(empleado.getNombre());
            empleadoActual.setEmail(empleado.getEmail());
            return true;
        }
    }

    public Boolean eliminarEmpleado(String id) throws EmpleadoException {
        Empleado empleado = null;
        boolean flagExiste = false;
        empleado = this.obtenerEmpleado(id);
        if (empleado == null) {
            throw new EmpleadoException("El empleado a eliminar no existe");
        } else {
            this.getListaEmpleados().remove(empleado);
            flagExiste = true;
            return flagExiste;
        }
    }

    public Empleado obtenerEmpleado(String cedula) {
        Empleado empleadoEncontrado = null;
        Iterator var3 = this.getListaEmpleados().iterator();

        while(var3.hasNext()) {
            Empleado empleado = (Empleado)var3.next();
            if (empleado.getId().equalsIgnoreCase(cedula)) {
                empleadoEncontrado = empleado;
                break;
            }
        }

        return empleadoEncontrado;
    }

    public Usuario crearUsuario(String id, String nombre, String email) throws EmpleadoException {
        Usuario nuevoUsuario = null;
        boolean usuarioExiste = this.verificarEmpleadoExistente(id);
        if (usuarioExiste) {
            throw new EmpleadoException("El usuario con cedula: " + id + " ya existe");
        } else {
            nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setId(id);
            nuevoUsuario.setEmail(email);
            this.getListaUsuarios().add(nuevoUsuario);
            return nuevoUsuario;
        }
    }

    public void agregarUsuario(Usuario nuevoUsuario) throws EmpleadoException {
        this.getListaUsuarios().add(nuevoUsuario);
    }

    public boolean verificarUsuarioExistente(String id) throws EmpleadoException {
        if (this.empleadoExiste(id)) {
            throw new EmpleadoException("El Usuario con cedula: " + id + " ya existe");
        } else {
            return false;
        }
    }

    public boolean usuarioExiste(String id) {
        boolean usuarioEncontrado = false;
        Iterator var3 = this.getListaUsuarios().iterator();

        while(var3.hasNext()) {
            Empleado empleado = (Empleado)var3.next();
            if (empleado.getId().equalsIgnoreCase(id)) {
                usuarioEncontrado = true;
                break;
            }
        }

        return usuarioEncontrado;
    }

    public boolean actualizarUsuario(String id, Usuario usuario) throws EmpleadoException {
        Usuario usuarioActual = this.obtenerUsuario(id);
        if (usuarioActual == null) {
            throw new EmpleadoException("El usuario a actualizar no existe");
        } else {
            usuarioActual.setId(usuario.getId());
            usuarioActual.setNombre(usuario.getNombre());
            usuarioActual.setEmail(usuario.getEmail());
            return true;
        }
    }

    public Boolean eliminarUsuario(String id) throws EmpleadoException {
        Usuario usuario = null;
        boolean flagExiste = false;
        usuario = this.obtenerUsuario(id);
        if (usuario == null) {
            throw new EmpleadoException("El usuario a eliminar no existe");
        } else {
            this.getListaUsuarios().remove(usuario);
            flagExiste = true;
            return flagExiste;
        }
    }

    public Usuario obtenerUsuario(String cedula) {
        Usuario usuarioEncontrado = null;
        Iterator var3 = this.getListaUsuarios().iterator();

        while(var3.hasNext()) {
            Usuario usuario = (Usuario) var3.next();
            if (usuario.getId().equalsIgnoreCase(cedula)) {
                usuarioEncontrado = usuario;
                break;
            }
        }

        return usuarioEncontrado;
    }



    public ArrayList<Empleado> obtenerEmpleados() {
        return null;
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return this.listaUsuarios;
    }

    public ArrayList<Empleado> getListaEmpleados() {
        return this.listaEmpleados;
    }

    public ArrayList<Evento> getListaEventos() {
        return this.listaEventos;
    }

    public ArrayList<Reserva> getListaReservas() {
        return this.listaReservas;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public void setListaEmpleados(ArrayList<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public void setListaEventos(ArrayList<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }

    public void setListaReservas(ArrayList<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }
}
