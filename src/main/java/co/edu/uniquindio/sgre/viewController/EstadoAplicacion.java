package co.edu.uniquindio.sgre.viewController;

import com.rabbitmq.client.DeliverCallback;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.IOException;

import static co.edu.uniquindio.sgre.SGREMain.COLA_SOLICITUD_RESERVA;
import static co.edu.uniquindio.sgre.SGREMain.channel;

public interface EstadoAplicacion {

    EstadoClass USUARIO = EstadoClass.getInstance();

    public default void actualizarEstado(String usuario, int tipoUsuario) {
        USUARIO.setUsuario(usuario);
        USUARIO.setTipoUsuario(tipoUsuario);
    }

    public default String getUsuario() {
        return USUARIO.getUsuario();
    }

    public default int getTipoUsuario() {
        return USUARIO.getTipoUsuario();
    }

    public default void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                Alert aler = new Alert(alertType);
                aler.setTitle(titulo);
                aler.setHeaderText(header);
                aler.setContentText(contenido);
                aler.showAndWait();
            }
        });
    }}

class EstadoClass {

    private String usuario;

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public static EstadoClass getInstance() {
        if (eINSTANCE == null) {
            eINSTANCE = new EstadoClass("", -1);
        }
        return eINSTANCE;
    }

    private int tipoUsuario;

    private static EstadoClass eINSTANCE;

    private EstadoClass(String usuario, int tipoUsuario) {
        this.usuario = usuario;
        this.tipoUsuario = tipoUsuario;
    }

}
