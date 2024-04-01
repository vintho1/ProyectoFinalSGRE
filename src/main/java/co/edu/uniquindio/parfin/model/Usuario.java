package co.edu.uniquindio.parfin.model;

//import lombok.*;
/*
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

 */
public class Usuario {
    private String id;
    private String nombre;
    private String email;

    public static UsuarioBuilder builder() {
        return new UsuarioBuilder();
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

    public Usuario(String id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public Usuario() {
    }

    public String toString() {
        String var10000 = this.getId();
        return "Usuario(id=" + var10000 + ", nombre=" + this.getNombre() + ", email=" + this.getEmail() + ")";
    }

    public static class UsuarioBuilder {
        private String id;
        private String nombre;
        private String email;

        UsuarioBuilder() {
        }

        public UsuarioBuilder id(String id) {
            this.id = id;
            return this;
        }

        public UsuarioBuilder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public UsuarioBuilder email(String email) {
            this.email = email;
            return this;
        }

        public Usuario build() {
            return new Usuario(this.id, this.nombre, this.email);
        }

        public String toString() {
            return "Usuario.UsuarioBuilder(id=" + this.id + ", nombre=" + this.nombre + ", email=" + this.email + ")";
        }
    }
}
