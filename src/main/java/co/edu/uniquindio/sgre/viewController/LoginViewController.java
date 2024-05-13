package co.edu.uniquindio.sgre.viewController;

import co.edu.uniquindio.sgre.exceptions.EmpleadoException;
import co.edu.uniquindio.sgre.model.Admin;
import co.edu.uniquindio.sgre.model.Empleado;
import co.edu.uniquindio.sgre.model.SGRE;
import co.edu.uniquindio.sgre.model.Usuario;
import co.edu.uniquindio.sgre.viewController.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginViewController {

    @FXML
    private Button btnInicio;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    private TextField txtUser;

    @FXML
    private AnchorPane ventana;

    private final SGRE sgre = SGRE.getInstance();

    public LoginViewController() throws EmpleadoException {
    }

    @FXML
    void inicioSesionEvent(ActionEvent event) {
        String usuario = txtUser.getText();
        String contrasenia = txtContrasenia.getText();

        try {
            if (sgre.verificarClienteAdministrador(usuario, contrasenia)) {
                // Credenciales correctas, redireccionar a la ventana principal
                mostrarAlerta("Inicio de sesión exitoso", "Bienvenido " + usuario);
                new ViewController(ventana, "/co/edu/uniquindio/sgre/sgre.fxml");
            } else {
                // Credenciales incorrectas, mostrar mensaje de error
                mostrarAlerta("Error", "Credenciales incorrectas");
            }
        } catch (EmpleadoException e) {
            mostrarAlerta("Error", e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void initialize() {

    }
}
