package co.edu.uniquindio.sgre.viewController;

import co.edu.uniquindio.sgre.controller.UsuarioController;
import co.edu.uniquindio.sgre.mapping.dto.UsuarioDto;
import co.edu.uniquindio.sgre.model.Usuario;
import co.edu.uniquindio.sgre.utils.Persistencia;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegistroViewController {
    UsuarioController usuarioControllerService;



    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtContrasenia;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtNombre;


    @FXML
    private AnchorPane ventana;

    @FXML
    void initialize() {
        usuarioControllerService = new UsuarioController();
        establecerPromptText();
    }

    @FXML
    void agregarUsuarioAction(ActionEvent event) throws IOException {
        crearusuario();
    }

    @FXML
    void cancelarAction(ActionEvent event) throws IOException {
        new ViewController(ventana, "login.fxml");
    }

    private void crearusuario() throws IOException {
        UsuarioDto usuarioDto = construirUsuarioDto();
        if (datosValidos(usuarioDto)) {
            if (usuarioControllerService.agregarUsuario(usuarioDto)) {
                mostrarMensaje("Notificación usuario", "Usuario creado", "El usuario se ha creado con éxito", Alert.AlertType.INFORMATION);
                new ViewController(ventana, "login.fxml");
            } else {
                mostrarMensaje("Notificación usuario", "Usuario no creado", "El usuario no se ha creado con éxito", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Notificación usuario", "Usuario no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }
        registrarAccionesSistema("Crear usuario", 1, "se creo en usuario con id: " + usuarioDto.id() + ", nombre: " + usuarioDto.nombre());
    }

    private void establecerPromptText() {
        txtNombre.setPromptText("Ingrese el nombre");
        txtCedula.setPromptText("Ingrese la cedula");
        txtCorreo.setPromptText("Ingrese el correo");
        txtCorreo.setPromptText("Ingrese el contraseña");
    }


    private UsuarioDto construirUsuarioDto() {
        return new UsuarioDto(
                txtCedula.getText(),
                txtNombre.getText(),
                txtCorreo.getText(),
                txtContrasenia.getText()
        );
    }

    private void limpiarCamposUsuario() {
        txtNombre.setText("");
        txtCedula.setText("");
        txtCorreo.setText("");
        txtContrasenia.setText("");
    }

    private boolean datosValidos(UsuarioDto usuarioDto) {
        String mensaje = "";
        if (usuarioDto.nombre() == null || usuarioDto.nombre().equals(""))
            mensaje += "El nombre es obligatorio \n";
        if (usuarioDto.id() == null || usuarioDto.id().equals(""))
            mensaje += "El id es obligatorio \n";
        if (usuarioDto.email() == null || usuarioDto.email().equals(""))
            mensaje += "El email es obligatorio \n";
        if (!mensaje.equals("")) {
            mostrarMensaje("Notificación cliente", "Datos invalidos", mensaje, Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }
}
