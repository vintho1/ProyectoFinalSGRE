package co.edu.uniquindio.sgre.viewController;

import co.edu.uniquindio.sgre.model.Admin;
import co.edu.uniquindio.sgre.model.Empleado;
import co.edu.uniquindio.sgre.model.Usuario;

public class SessionManager {
    private static SessionManager instance;
    private Usuario usuarioActivo;
    private Admin adminActivo;
    private Empleado empleadoActivo;

    private SessionManager() {

    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleadoActivo = empleado;
    }

    public Empleado getEmpleado() {
        return empleadoActivo;
    }


    public void setUsuario(Usuario usuario) {
        this.usuarioActivo = usuario;
    }



    public Usuario getUsuario() {
        return usuarioActivo;
    }

    public void setAdmin(Admin admin) {
        this.adminActivo = admin;
    }

    public Admin getAdmin() {
        return adminActivo;
    }

    public boolean isUserLoggedIn() {
        return usuarioActivo != null;
    }

    public boolean isAdminLoggedIn() {
        return adminActivo != null;
    }

    public void cerrarSesion() {
        usuarioActivo = null;
        adminActivo = null;
    }
}
