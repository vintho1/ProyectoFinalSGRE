package co.edu.uniquindio.sgre.utils;

import co.edu.uniquindio.sgre.model.*;

import java.time.LocalDate;

public class SGREUtils {

    public static SGRE inicializarDatos() {
        SGRE sgre = new SGRE();

        Empleado empleado1 = new Empleado();
        empleado1.setNombre("juan");
        empleado1.setId("125454");
        empleado1.setEmail("juab@gmail.com");
        empleado1.setContrasenia("1");
        sgre.getListaEmpleados().add(empleado1);

        Empleado empleado2 = new Empleado();
        empleado2.setNombre("ana");
        empleado2.setId("12545445345");
        empleado2.setEmail("ana@gmail.com");
        empleado2.setContrasenia("12");
        sgre.getListaEmpleados().add(empleado2);

        Empleado empleado3 = new Empleado();
        empleado3.setNombre("camila");
        empleado3.setId("1254");
        empleado3.setEmail("cam21dun@gmail.com");

        empleado3.setContrasenia("123");
        sgre.getListaEmpleados().add(empleado3);

        Usuario usuario = new Usuario();
        usuario.setNombre("camila");
        usuario.setId("125454");
        usuario.setEmail("camil@gmail.com");

        usuario.setContrasenia("123");
        sgre.getListaUsuarios().add(usuario);

        Evento evento1 = new Evento();
        evento1.setId("1");
        evento1.setNombre("Conferencia de Tecnología");
        evento1.setDescripcion("Conferencia sobre los últimos avances en tecnología.");
        evento1.setFecha(LocalDate.now().plusDays(7).toString());
        evento1.setCapMax("100");
        evento1.setEmpleadoAsignado(empleado1);
        sgre.getListaEventos().add(evento1);

        Evento evento2 = new Evento();
        evento2.setId("2");
        evento2.setNombre("Taller de Programación");
        evento2.setDescripcion("Taller práctico sobre programación en Java.");
        evento2.setFecha(LocalDate.now().plusMonths(1).toString());
        evento2.setCapMax("50");
        evento2.setEmpleadoAsignado(empleado2);
        sgre.getListaEventos().add(evento2);


        Reserva reserva1 = new Reserva();
        reserva1.setId("11");
        reserva1.setEvento(evento1);
        reserva1.setUsuario(usuario);
        reserva1.setFecha(LocalDate.now().toString());
        reserva1.setEstado(Estado.APROBADA);
       // evento1.getListaReservas().add(reserva1);
        sgre.getListaReservas().add(reserva1);

        Reserva reserva2 = new Reserva();
        reserva2.setId("22");
        reserva2.setEvento(evento2);
        reserva2.setUsuario(usuario);
        reserva2.setFecha(LocalDate.now().plusDays(1).toString());
        reserva2.setEstado(Estado.PENDIENTE);
       // evento2.getListaReservas().add(reserva2);
        sgre.getListaReservas().add(reserva2);

        Admin admin1 = new Admin();
        admin1.setCorreo("1");
        admin1.setContrasenia("1");
        sgre.getListaAdmins().add(admin1);

        Admin admin2 = new Admin();
        admin2.setCorreo("camila");
        admin2.setContrasenia("321");
        sgre.getListaAdmins().add(admin2);


        System.out.println("Información inicializada del sgre creada");
        return sgre;


    }
}
