package co.edu.uniquindio.sgre.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class InicioViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCrearCuenta;

    @FXML
    private Button btnInicioSesion;

    @FXML
    private AnchorPane ventana;


    @FXML
    void InicioSesionAction(ActionEvent event) throws IOException {
        new ViewController(ventana, "/co/edu/uniquindio/sgre/login.fxml");

    }

    @FXML
    void crearCuentaAction(ActionEvent event) throws IOException {
        new ViewController(ventana, "/co/edu/uniquindio/sgre/sgre.fxml");

    }

    @FXML
    void initialize() {

    }

}
