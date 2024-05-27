package co.edu.uniquindio.sgre.model;

//import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
/*
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

 */
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private Usuario usuario;
    private Evento evento;
    private String fecha;
    private Estado estado;

    public Reserva(String id,  Usuario usuario, Evento evento, String fecha, Estado estado) {
        this.id = id;

        this.usuario = usuario;
        this.evento = evento;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Reserva() {
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id='" + id + '\'' +
                ", usuario=" + usuario +
                ", evento=" + evento +
                ", fecha=" + fecha +
                ", estado=" + estado +
                '}';
    }
}
