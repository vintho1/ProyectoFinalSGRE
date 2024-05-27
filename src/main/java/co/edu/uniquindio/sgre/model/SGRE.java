package co.edu.uniquindio.sgre.model;

import co.edu.uniquindio.sgre.exceptions.EmpleadoException;
import co.edu.uniquindio.sgre.exceptions.UsuarioException;
import co.edu.uniquindio.sgre.model.services.ISGREService;
import co.edu.uniquindio.sgre.utils.Persistencia;
import co.edu.uniquindio.sgre.utils.SGREUtils;
import co.edu.uniquindio.sgre.viewController.SessionManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static co.edu.uniquindio.sgre.utils.SGREUtils.inicializarDatos;

public class SGRE implements ISGREService, Serializable {
    private static final long serialVersionUID = 1L;
    private static SGRE sgre;
    ArrayList<Usuario> listaUsuarios = new ArrayList();
    ArrayList<Empleado> listaEmpleados = new ArrayList();
    ArrayList<Evento> listaEventos = new ArrayList();
    ArrayList<Reserva> listaReservas = new ArrayList();
    ArrayList<Admin> listaAdmins = new ArrayList();

    public SGRE() {
        listaAdmins = new ArrayList<>();
        listaAdmins.add(new Admin("Camila", "123"));
    }


    public static SGRE getInstance() throws EmpleadoException {
        if (sgre == null) {
            sgre = inicializarDatos();
            sgre.setListaEmpleados(Persistencia.cargarEmpleados());
            sgre.setListaUsuarios(Persistencia.cargarUsuarios());
            sgre.setListaEventos(Persistencia.cargarEventos());
            sgre.setListaReservas(Persistencia.cargarReservas());
        }
        return sgre;
    }

    public void actualizarEstado(){
        sgre.setListaEmpleados(Persistencia.cargarEmpleados());
        sgre.setListaUsuarios(Persistencia.cargarUsuarios());
        sgre.setListaEventos(Persistencia.cargarEventos());
        sgre.setListaReservas(Persistencia.cargarReservas());
    }



    ///////Crud empleado /////////////

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

    ////////////Crud usuario///////////////

    public Usuario crearUsuario(String id, String nombre, String email, String usuario, String contrasenia) throws UsuarioException {
        Usuario nuevoUsuario = null;
        boolean usuarioExiste = this.verificarUsuarioExistente(id);
        if (usuarioExiste) {
            throw new UsuarioException("El usuario con cedula: " + id + " ya existe");
        } else {
            nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setId(id);
            nuevoUsuario.setEmail(email);
            nuevoUsuario.setContrasenia(contrasenia);
            this.getListaUsuarios().add(nuevoUsuario);
            return nuevoUsuario;
        }
    }

    public void agregarUsuario(Usuario nuevoUsuario) throws UsuarioException {
        this.getListaUsuarios().add(nuevoUsuario);
    }

    public boolean verificarUsuarioExistente(String id) throws UsuarioException {
        if (this.usuarioExiste(id)) {
            throw new UsuarioException("El Usuario con cedula: " + id + " ya existe");
        } else {
            return false;
        }
    }

    public List<Reserva> obtenerReservasUsuario(String cedula){

        return  listaReservas.stream().filter(reserva -> reserva.getId().equals(cedula)).collect(Collectors.toList());
    }

    public boolean usuarioExiste(String id) {
        boolean usuarioEncontrado = false;
        Iterator var3 = this.getListaUsuarios().iterator();

        while(var3.hasNext()) {
            Usuario empleado = (Usuario) var3.next();
            if (empleado.getId().equalsIgnoreCase(id)) {
                usuarioEncontrado = true;
                break;
            }
        }

        return usuarioEncontrado;
    }

    public boolean actualizarUsuario(String id, Usuario usuario) throws UsuarioException {
        Usuario usuarioActual = this.obtenerUsuario(id);
        if (usuarioActual == null) {
            throw new UsuarioException("El usuario a actualizar no existe");
        } else {
            usuarioActual.setId(usuario.getId());
            usuarioActual.setNombre(usuario.getNombre());
            usuarioActual.setEmail(usuario.getEmail());
            usuarioActual.setContrasenia(usuario.getContrasenia());
            return true;
        }
    }

    public Boolean eliminarUsuario(String id) throws UsuarioException {
        Usuario usuario = null;
        boolean flagExiste = false;
        usuario = this.obtenerUsuario(id);
        if (usuario == null) {
            throw new UsuarioException("El usuario a eliminar no existe");
        } else {
            this.getListaUsuarios().remove(usuario);
            flagExiste = true;
            return flagExiste;
        }
    }

    public  Usuario obtenerUsuario(String user) {
        Usuario usuarioEncontrado = null;
        Iterator var3 = this.getListaUsuarios().iterator();

        while(var3.hasNext()) {
            Usuario usuario = (Usuario) var3.next();
            if (usuario.getEmail().equalsIgnoreCase(user)) {
                usuarioEncontrado = usuario;
                break;
            }
        }

        return usuarioEncontrado;
    }
    public  Usuario obtenerUsuario3(String user) {
        Usuario usuarioEncontrado = null;
        Iterator var3 = this.getListaUsuarios().iterator();

        while(var3.hasNext()) {
            Usuario usuario = (Usuario) var3.next();
            if (usuario.getEmail().equalsIgnoreCase(user)) {
                usuarioEncontrado = usuario;
                break;
            }
        }

        return usuarioEncontrado;
    }

    public Usuario obtenerUsuario2(String nombreUsuario) {
        Usuario usuarioEncontrado = null;
        Iterator var3 = this.getListaUsuarios().iterator();

        while(var3.hasNext()) {
            Usuario usuario = (Usuario) var3.next();
            if (usuario.getEmail().equalsIgnoreCase(nombreUsuario)) {
                usuarioEncontrado = usuario;
                break;
            }
        }

        return usuarioEncontrado;
    }
/*
    public boolean verificarClienteAdministrador(String cedula, String contrasena) throws EmpleadoException {
        if (cedula.isEmpty()) {
            throw new EmpleadoException("Usuario vacío");
        }
        if (contrasena.isEmpty()) {
            throw new EmpleadoException("Contraseña vacía");
        }

        Admin admin = obtenerAdministrador(cedula);
        if (admin != null && admin.getContrasenia().equals(contrasena)) {
            return true;
        }

        Usuario usuario = obtenerUsuario(cedula);
        if (usuario != null && usuario.getContrasenia().equals(contrasena)) {
            return true;
        }

        Empleado empleado = obtenerEmpleado(cedula);
        if (empleado != null && empleado.getContrasenia().equals(contrasena)) {
            return true;
        }
        return false;
    }
    public boolean verificarEmpleado(String usuario, String contrasenia) {

        for (Empleado empleado : listaEmpleados) {
            if (empleado.getUsuario().equals(usuario) && empleado.getContrasenia().equals(contrasenia)) {
                return true;
            }
        }
        return false; // Credenciales inválidas
    }

 */

    public boolean verificarAdmin(String correo, String contrasenia) {
        for (Admin admin : listaAdmins) {
            if (admin.getCorreo().equals(correo) && admin.getContrasenia().equals(contrasenia)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificarUser(String usuario, String contrasenia) {
        for (Usuario user : listaUsuarios) {
            if (user.getEmail().equals(usuario) && user.getContrasenia().equals(contrasenia)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificarEmpleado(String usuario, String contrasenia) {
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getEmail().equals(usuario) && empleado.getContrasenia().equals(contrasenia)) {
                return true;
            }
        }
        return false;
    }






    public Admin obtenerAdministrador(String user) {
        return listaAdmins.stream()
                .filter(admin -> admin.getCorreo().equals(user))
                .findFirst()
                .orElse(null);
    }

    ////

    public Evento crearEvento(String id, String nombre, String descripcion, String fecha, int capMax, Empleado empleadoAsignado) {
        Evento nuevoEvento = new Evento(id, nombre, descripcion, fecha, capMax, empleadoAsignado);
        this.getListaEventos().add(nuevoEvento);
        return nuevoEvento;
    }

    public void agregarEvento(Evento nuevoEvento) {
        this.getListaEventos().add(nuevoEvento);
    }

    public boolean verificarEventoExistente(String id) {
        return this.eventoExiste(id);
    }

    public boolean eventoExiste(String id) {
        return this.getListaEventos().stream().anyMatch(evento -> evento.getId().equalsIgnoreCase(id));
    }

    public boolean actualizarEvento(String id, Evento evento) {
        Evento eventoActual = this.obtenerEvento(id);
        if (eventoActual != null) {
            eventoActual.setId(evento.getId());
            eventoActual.setNombre(evento.getNombre());
            eventoActual.setDescripcion(evento.getDescripcion());
            eventoActual.setFecha(evento.getFecha());
            eventoActual.setCapMax(String.valueOf(evento.getCapMax()));
            eventoActual.setEmpleadoAsignado(evento.getEmpleadoAsignado());
            return true;
        }
        return false;
    }

    public boolean eliminarEvento(String id) {
        Iterator<Evento> iterator = this.getListaEventos().iterator();
        while (iterator.hasNext()) {
            Evento evento = iterator.next();
            if (evento.getId().equalsIgnoreCase(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public Evento obtenerEvento(String id) {
        return this.getListaEventos().stream().filter(evento -> evento.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
    }

    public ArrayList<Evento> obtenerEventos() {
        return this.getListaEventos();
    }


    ///////

    public Reserva crearReserva(String id, String capacidad, Usuario usuario, Evento evento, String fecha, Estado estado) throws EmpleadoException {
        Reserva nuevaReserva = null;
        boolean reservaExiste = this.verificarReservaExistente(id);
        if (reservaExiste) {
            throw new EmpleadoException("La reserva con ID: " + id + " ya existe");
        } else {
            nuevaReserva = new Reserva(id,  usuario, evento, fecha, estado);
            this.getListaReservas().add(nuevaReserva);
            return nuevaReserva;
        }
    }

    public void agregarReserva(Reserva nuevaReserva) throws EmpleadoException {
        if (verificarReservaExistente(nuevaReserva.getId())) {
            throw new EmpleadoException("La reserva con ID: " + nuevaReserva.getId() + " ya existe");
        }
        this.getListaReservas().add(nuevaReserva);
    }

    public boolean verificarReservaExistente(String id) {
        return this.reservaExiste(id);
    }

    public boolean reservaExiste(String id) {
        return this.getListaReservas().stream().anyMatch(reserva -> reserva.getId().equalsIgnoreCase(id));
    }

    public boolean actualizarReserva(String id, Reserva reserva) {
        Iterator<Reserva> iterator = this.getListaReservas().iterator();
        while (iterator.hasNext()) {
            Reserva reservaActual = iterator.next();
            if (reservaActual.getId().equalsIgnoreCase(id)) {
                iterator.remove();
                this.getListaReservas().add(reserva);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarReserva(String id) {
        Iterator<Reserva> iterator = this.getListaReservas().iterator();
        while (iterator.hasNext()) {
            Reserva reserva = iterator.next();
            if (reserva.getId().equalsIgnoreCase(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public Reserva obtenerReserva(String id) {
        return this.getListaReservas().stream().filter(reserva -> reserva.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
    }

    public ArrayList<Reserva> obtenerReservas() {
        return this.getListaReservas();
    }



    public ArrayList<Usuario> obtenerUsuario() {
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

    public ArrayList<Admin> getListaAdmins() {
        return listaAdmins;
    }

    public void setListaAdmins(ArrayList<Admin> listaAdmins) {
        this.listaAdmins = listaAdmins;
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
