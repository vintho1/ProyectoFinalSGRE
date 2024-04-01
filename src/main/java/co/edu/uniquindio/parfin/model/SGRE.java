package co.edu.uniquindio.parfin.model;

import co.edu.uniquindio.parfin.exceptions.EmpleadoException;
import co.edu.uniquindio.parfin.model.services.ISGREService;
import java.util.ArrayList;
import java.util.Iterator;

public class SGRE implements ISGREService {
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
