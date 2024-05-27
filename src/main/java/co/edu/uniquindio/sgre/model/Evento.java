package co.edu.uniquindio.sgre.model;

//import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/*@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

 */
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    private String descripcion;
    private String fecha;
    private int capMax;
    private Empleado empleadoAsignado;
    private ArrayList<Reserva> listaReservas;

    public Evento(String id, String nombre, String descripcion, String fecha, int capMax, Empleado empleadoAsignado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.capMax = capMax;
        this.empleadoAsignado = empleadoAsignado;
       listaReservas = new ArrayList<Reserva>();
    }

    public Evento(String id, String nombre, String capMax) {
        this.id = id;
        this.nombre = nombre;
        this.capMax = 0 ;

    }

    public Evento() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCapMax() {
        return capMax;
    }

    public void setCapMax(String capMax) {
        this.capMax = 0 ;
    }

    public Empleado getEmpleadoAsignado() {
        return empleadoAsignado;
    }

    public void setEmpleadoAsignado(Empleado empleadoAsignado) {
        this.empleadoAsignado = empleadoAsignado;
    }

 /*   public ArrayList<Reserva> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(ArrayList<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }

  */

    @Override
    public String toString() {
        return "Evento{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", capMax='" + capMax + '\'' +
                ", empleadoAsignado=" + empleadoAsignado +
          //      ", listaReservas=" + listaReservas +
                '}';
    }
}
