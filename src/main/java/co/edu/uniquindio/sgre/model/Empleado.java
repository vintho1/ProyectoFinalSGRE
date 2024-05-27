package co.edu.uniquindio.sgre.model;

//import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

/*@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

 */
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    private String email;
    private String contrasenia;
    private RolEmpleado rolEmpleado;
    private ArrayList<Evento> listaEventos;


    public Empleado(String id, String nombre, String email,  String contrasenia, RolEmpleado rolEmpleado) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
        this.rolEmpleado = rolEmpleado;
        listaEventos = new ArrayList<Evento>();
    }

    public Empleado() {
    }

    public static EmpleadoBuilder builder() {
        return new EmpleadoBuilder();
    }

    public RolEmpleado getRolEmpleado() {
        return rolEmpleado;
    }

    public void setRolEmpleado(RolEmpleado rolEmpleado) {
        this.rolEmpleado = rolEmpleado;
    }

    public String getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getEmail() {
        return this.email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String toString() {
        String var10000 = this.getId();
        return "Empleado(id=" + var10000 + ", nombre=" + this.getNombre() + ", email=" + this.getEmail() + ")";
    }

    public static class EmpleadoBuilder {
        private String id;
        private String nombre;
        private String email;
        private RolEmpleado rol;
        private String contrasenia;



        EmpleadoBuilder() {
        }

        public EmpleadoBuilder id(String id) {
            this.id = id;
            return this;
        }

        public EmpleadoBuilder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public EmpleadoBuilder email(String email) {
            this.email = email;
            return this;
        }

        public EmpleadoBuilder contrasenia(String email) {
            this.email = email;
            return this;
        }

        public EmpleadoBuilder rol(String email) {
            this.email = email;
            return this;
        }

        public Empleado build() {
            return new Empleado(this.id, this.nombre, this.email,this.contrasenia, this.rol);
        }

        public String toString() {
            return "Empleado.EmpleadoBuilder(id=" + this.id + ", nombre=" + this.nombre + ", email=" + this.email + ")";
        }
    }
}
