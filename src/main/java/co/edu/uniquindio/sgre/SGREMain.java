package co.edu.uniquindio.sgre;

import co.edu.uniquindio.sgre.viewController.InicioViewController;
import co.edu.uniquindio.sgre.viewController.EventoViewController;
import co.edu.uniquindio.sgre.viewController.LoginViewController;
import co.edu.uniquindio.sgre.viewController.SGREViewController;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SGREMain extends Application {

    public final static String COLA_SOLICITUD_RESERVA = "cola_001";
    public final static String COLA_RESERVA_VALIDADA = "cola_002";

    private Stage primaryStage;
    private BorderPane rootLayout;

    public static Channel channel;



    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        this.primaryStage.setTitle("SGRE");
        Image icon = new Image(getClass().getResourceAsStream("/persistencia/imagenes/logo.jpg"));
        stage.getIcons().add(icon);
        mostrarVentanaPrincipal();
    }

    public void mostrarVentanaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("login.fxml"));
            AnchorPane rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection connection = null;
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            connection = factory.newConnection();
            channel = connection.createChannel();

            System.out.println(" [*] Application started. To exit press CTRL+C");

            launch();
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(channel!=null) channel.close();
                if(connection!=null) connection.close();
            } catch (IOException | TimeoutException e) {
                throw new RuntimeException(e);
            }

        }
    }
}