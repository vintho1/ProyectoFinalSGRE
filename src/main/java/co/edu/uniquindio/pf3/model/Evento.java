package co.edu.uniquindio.model;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Evento {
    private String id;
    private Empleado empleado;
    private Evento evento;
    private LocalDate fechaSolicitud;
    private Estado estado;
}
