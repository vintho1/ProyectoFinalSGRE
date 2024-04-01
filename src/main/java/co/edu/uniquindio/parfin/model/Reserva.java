package co.edu.uniquindio.parfin.model;

//import lombok.*;

import java.time.LocalDate;
/*
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

 */
public class Reserva {
    private String id;
    private String nombre;
    private String descripcion;
    private LocalDate fecha;
    private String capMax;
    private Empleado empleadoAsignado;
    private Reserva reservaAsignada;

    public static ReservaBuilder builder() {
        return new ReservaBuilder();
    }

    public String getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public String getCapMax() {
        return this.capMax;
    }

    public Empleado getEmpleadoAsignado() {
        return this.empleadoAsignado;
    }

    public Reserva getReservaAsignada() {
        return this.reservaAsignada;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setCapMax(String capMax) {
        this.capMax = capMax;
    }

    public void setEmpleadoAsignado(Empleado empleadoAsignado) {
        this.empleadoAsignado = empleadoAsignado;
    }

    public void setReservaAsignada(Reserva reservaAsignada) {
        this.reservaAsignada = reservaAsignada;
    }

    public Reserva(String id, String nombre, String descripcion, LocalDate fecha, String capMax, Empleado empleadoAsignado, Reserva reservaAsignada) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.capMax = capMax;
        this.empleadoAsignado = empleadoAsignado;
        this.reservaAsignada = reservaAsignada;
    }

    public Reserva() {
    }

    public String toString() {
        String var10000 = this.getId();
        return "Reserva(id=" + var10000 + ", nombre=" + this.getNombre() + ", descripcion=" + this.getDescripcion() + ", fecha=" + this.getFecha() + ", capMax=" + this.getCapMax() + ", empleadoAsignado=" + this.getEmpleadoAsignado() + ", reservaAsignada=" + this.getReservaAsignada() + ")";
    }

    public static class ReservaBuilder {
        private String id;
        private String nombre;
        private String descripcion;
        private LocalDate fecha;
        private String capMax;
        private Empleado empleadoAsignado;
        private Reserva reservaAsignada;

        ReservaBuilder() {
        }

        public ReservaBuilder id(String id) {
            this.id = id;
            return this;
        }

        public ReservaBuilder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public ReservaBuilder descripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public ReservaBuilder fecha(LocalDate fecha) {
            this.fecha = fecha;
            return this;
        }

        public ReservaBuilder capMax(String capMax) {
            this.capMax = capMax;
            return this;
        }

        public ReservaBuilder empleadoAsignado(Empleado empleadoAsignado) {
            this.empleadoAsignado = empleadoAsignado;
            return this;
        }

        public ReservaBuilder reservaAsignada(Reserva reservaAsignada) {
            this.reservaAsignada = reservaAsignada;
            return this;
        }

        public Reserva build() {
            return new Reserva(this.id, this.nombre, this.descripcion, this.fecha, this.capMax, this.empleadoAsignado, this.reservaAsignada);
        }

        public String toString() {
            return "Reserva.ReservaBuilder(id=" + this.id + ", nombre=" + this.nombre + ", descripcion=" + this.descripcion + ", fecha=" + this.fecha + ", capMax=" + this.capMax + ", empleadoAsignado=" + this.empleadoAsignado + ", reservaAsignada=" + this.reservaAsignada + ")";
        }
    }
}
