package co.edu.uniquindio.sgre.viewController;

import co.edu.uniquindio.sgre.model.Admin;
import co.edu.uniquindio.sgre.model.Usuario;

public class SessionManager {
    private static SessionManager instance;
    private Usuario usuarioActivo;
    private Admin adminActivo;

    private SessionManager() {
        // Constructor privado para evitar la creación de instancias externas
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
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
