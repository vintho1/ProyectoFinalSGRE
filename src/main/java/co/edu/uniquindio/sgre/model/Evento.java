package co.edu.uniquindio.sgre.model;

//import lombok.*;

import java.time.LocalDate;
/*@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

 */
public class Evento {
    private String id;
    private Empleado empleado;
    private Evento evento;
    private LocalDate fechaSolicitud;
    private Estado estado;
    public static EventoBuilder builder() {
        return new EventoBuilder();
    }

    public String getId() {
        return this.id;
    }

    public Empleado getEmpleado() {
        return this.empleado;
    }

    public Evento getEvento() {
        return this.evento;
    }

    public LocalDate getFechaSolicitud() {
        return this.fechaSolicitud;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Evento(String id, Empleado empleado, Evento evento, LocalDate fechaSolicitud, Estado estado) {
        this.id = id;
        this.empleado = empleado;
        this.evento = evento;
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
    }

    public Evento() {
    }

    public String toString() {
        String var10000 = this.getId();
        return "Evento(id=" + var10000 + ", empleado=" + this.getEmpleado() + ", evento=" + this.getEvento() + ", fechaSolicitud=" + this.getFechaSolicitud() + ", estado=" + this.getEstado() + ")";
    }

    public static class EventoBuilder {
        private String id;
        private Empleado empleado;
        private Evento evento;
        private LocalDate fechaSolicitud;
        private Estado estado;

        EventoBuilder() {
        }

        public EventoBuilder id(String id) {
            this.id = id;
            return this;
        }

        public EventoBuilder empleado(Empleado empleado) {
            this.empleado = empleado;
            return this;
        }

        public EventoBuilder evento(Evento evento) {
            this.evento = evento;
            return this;
        }

        public EventoBuilder fechaSolicitud(LocalDate fechaSolicitud) {
            this.fechaSolicitud = fechaSolicitud;
            return this;
        }

        public EventoBuilder estado(Estado estado) {
            this.estado = estado;
            return this;
        }

        public Evento build() {
            return new Evento(this.id, this.empleado, this.evento, this.fechaSolicitud, this.estado);
        }

        public String toString() {
            return "Evento.EventoBuilder(id=" + this.id + ", empleado=" + this.empleado + ", evento=" + this.evento + ", fechaSolicitud=" + this.fechaSolicitud + ", estado=" + this.estado + ")";
        }
    }
}
