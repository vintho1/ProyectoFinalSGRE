package co.edu.uniquindio.sgre.utils;

import co.edu.uniquindio.sgre.model.Empleado;
import co.edu.uniquindio.sgre.model.SGRE;

public class SGREUtils {

    public static SGRE inicializarDatos() {
        SGRE sgre = new SGRE();

        Empleado empleado = new Empleado();
        empleado.setId("125454");
        empleado.setNombre("juan");
        empleado.setEmail("juab@gmail.com");
        sgre.getListaEmpleados().add(empleado);
        System.out.println("Información del banco creada");
        return sgre;


    }
}
