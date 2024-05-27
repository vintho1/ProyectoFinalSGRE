package co.edu.uniquindio.sgre.viewController;

import co.edu.uniquindio.sgre.controller.SGREController;
import co.edu.uniquindio.sgre.controller.service.ISGREControllerService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class SGREViewController implements EstadoAplicacion {


    @FXML
    public Tab tabEmpleado;
    @FXML
    public Tab tabUsuario;
    @FXML
    public Tab tabReserva;
    @FXML
    public Tab tabEvento;
    @FXML
    public Label tipoUsuario;

    ISGREControllerService sgreControllerService;
    @FXML
    private AnchorPane ventana;

    @FXML
    void PerfilEvent(ActionEvent event) {

    }


    @FXML
    void initialize() {
        sgreControllerService = new SGREController();
        if (getTipoUsuario() == 0) {
            tabEmpleado.setDisable(false);
            tabUsuario.setDisable(false);
            tabReserva.setDisable(true);
            tabEvento.setDisable(false);
            tipoUsuario.setText("ADMIN: "+ getUsuario());
        }
        if (getTipoUsuario() == 1) {
            tabEmpleado.setDisable(true);
            tabUsuario.setDisable(true);
            tabReserva.setDisable(false);
            tabEvento.setDisable(false);
            tipoUsuario.setText("EMPLEADO: "+ getUsuario());
        }
        if (getTipoUsuario() == 2) {
            tabEmpleado.setDisable(true);
            tabUsuario.setDisable(false);
            tabReserva.setDisable(false);
            tabEvento.setDisable(false);
            tipoUsuario.setText("USUARIO: "+ getUsuario());
        }
    }

    @Override
    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
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
    }
}