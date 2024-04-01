package co.edu.uniquindio.model;

import co.edu.uniquindio.exceptions.EmpleadoException;
import co.edu.uniquindio.model.services.ISGREService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
@Getter
@Setter
public class SGRE implements ISGREService {

    private static final long serialVersionUID = 1L;

    ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    ArrayList<Empleado> listaEmpleados = new ArrayList<>();
    ArrayList<Evento> listaEventos = new ArrayList<>();
    ArrayList<Reserva> listaReservas = new ArrayList<>();

    public SGRE() {
    }


    @Override
    public Empleado crearEmpleado(String id, String nombre, String email) throws EmpleadoException {
        Empleado nuevoEmpleado = null;
        boolean empleadoExiste = verificarEmpleadoExistente(id);
        if(empleadoExiste){
            throw new EmpleadoException("El empleado con cedula: "+id+" ya existe");
        }else{
            nuevoEmpleado = new Empleado();
            nuevoEmpleado.setNombre(nombre);
            nuevoEmpleado.setId(id);
            nuevoEmpleado.setEmail(email);
            getListaEmpleados().add(nuevoEmpleado);
        }
        return nuevoEmpleado;
    }

    public void agregarEmpleado(Empleado nuevoEmpleado) throws EmpleadoException{
        getListaEmpleados().add(nuevoEmpleado);
    }
    @Override
    public boolean verificarEmpleadoExistente(String id) throws EmpleadoException {
        if(empleadoExiste(id)){
            throw new EmpleadoException("El empleado con cedula: "+id+" ya existe");
        }else{
            return false;
        }
    }
    public boolean empleadoExiste(String id) {
        boolean empleadoEncontrado = false;
        for (Empleado empleado : getListaEmpleados()) {
            if(empleado.getId().equalsIgnoreCase(id)){
                empleadoEncontrado = true;
                break;
            }
        }
        return empleadoEncontrado;
    }
    @Override
    public boolean actualizarEmpleado(String id, Empleado empleado) throws EmpleadoException {
        Empleado empleadoActual = obtenerEmpleado(id);
        if(empleadoActual == null)
            throw new EmpleadoException("El empleado a actualizar no existe");
        else{
            empleadoActual.setId(empleado.getId());
            empleadoActual.setNombre(empleado.getNombre());
            empleadoActual.setEmail(empleado.getEmail());

            return true;
        }
    }
    @Override
    public Boolean eliminarEmpleado(String id
    ) throws EmpleadoException {
        Empleado empleado = null;
        boolean flagExiste = false;
        empleado = obtenerEmpleado(id);
        if(empleado == null)
            throw new EmpleadoException("El empleado a eliminar no existe");
        else{
            getListaEmpleados().remove(empleado);
            flagExiste = true;
        }
        return flagExiste;
    }
    @Override
    public Empleado obtenerEmpleado(String cedula) {
        Empleado empleadoEncontrado = null;
        for (Empleado empleado : getListaEmpleados()) {
            if(empleado.getId().equalsIgnoreCase(cedula)){
                empleadoEncontrado = empleado;
                break;
            }
        }
        return empleadoEncontrado;
    }

    @Override
    public ArrayList<Empleado> obtenerEmpleados() {
        return null;
    }


}
