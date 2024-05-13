package co.edu.uniquindio.sgre.model;

import java.io.Serializable;

public class Admin implements Serializable {
    private static final long serialVersioUID = 1L;
    private String usuario;
    private String contrasenia;

    public Admin() {
    }

    public Admin(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "usuario='" + usuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                '}';
    }
}
