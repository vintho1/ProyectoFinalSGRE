package co.edu.uniquindio.sgre.viewController;

import co.edu.uniquindio.sgre.SGREMain;
import co.edu.uniquindio.sgre.exceptions.EmpleadoException;
import co.edu.uniquindio.sgre.model.SGRE;
import com.rabbitmq.client.DeliverCallback;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static co.edu.uniquindio.sgre.SGREMain.*;

public class LoginViewController implements EstadoAplicacion {

    @FXML
    private Button btnInicio;

    @FXML
    private Button btnuno;

    @FXML
    private Button btnotro;


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
        String correo = txtUser.getText();
        String contrasenia = txtContrasenia.getText();

        if (correo.isEmpty() || contrasenia.isEmpty()) {
            mostrarAlerta("Error", "Por favor ingresa correo y contraseña");
            return;
        }

        try {
            if (sgre.verificarAdmin(correo, contrasenia)) {
                actualizarEstado(correo, 0);
                new ViewController(ventana, "sgre.fxml");
            } else if (sgre.verificarEmpleado(correo, contrasenia)) {
                actualizarEstado(correo, 1);
                subscribirAColaEmpleado();
                mostrarAlerta("Inicio de sesión exitoso", "Bienvenido " + correo);
                new ViewController(ventana, "sgre.fxml");
            } else if (sgre.verificarUser(correo, contrasenia)) {
                actualizarEstado(correo, 2);
                System.out.println(sgre.obtenerUsuario(correo).getEmail());
                subscribirAColaUsuario(sgre.obtenerUsuario(correo).getEmail());
                mostrarAlerta("Inicio de sesión exitoso", "Bienvenido " + correo);
                new ViewController(ventana, "sgre.fxml");
            } else {
                mostrarAlerta("Error", "Credenciales incorrectas");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void subscribirAColaEmpleado() throws IOException {
        channel.exchangeDeclare(COLA_SOLICITUD_RESERVA, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, COLA_SOLICITUD_RESERVA, "");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            mostrarMensaje("Notificación evento", "Reserva solicitada", message, Alert.AlertType.INFORMATION);
            System.out.println(" [x] Received '" + message + "' por el Empleado");
            sgre.actualizarEstado();
        };

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }

    private void subscribirAColaUsuario(String correo) throws IOException {
        channel.exchangeDeclare(COLA_RESERVA_VALIDADA, "direct");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, COLA_RESERVA_VALIDADA, correo);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Reserva validada con respuesta: '" + message + "'");
            mostrarMensaje("Notificación evento", "Reserva actualizada", message, Alert.AlertType.INFORMATION);
            mostrarAlerta("Estado de reserva actualizada", message);
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }


    @FXML
    void registrarEvent(ActionEvent event) throws IOException {
        registrarAction();

    }

    @FXML
    void enviarMensaje(ActionEvent event) throws IOException {
    }

    @FXML
    void reservaValidada(ActionEvent event) throws IOException {
        channel.basicPublish(COLA_RESERVA_VALIDADA, "q", null, "Reserva validada".getBytes(StandardCharsets.UTF_8));
    }

    private void registrarAction() throws IOException {
        new ViewController(ventana, "registro.fxml");
    }


    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void initialize() {

    }

    @Override
    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Platform.runLater(new Runnable() {
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