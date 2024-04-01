package co.edu.uniquindio.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reserva {
    private String id;
    private String nombre;
    private String descripcion;
    private LocalDate fecha;
    private String capMax;
    private Empleado empleadoAsignado;
    private Reserva reservaAsignada;
}
