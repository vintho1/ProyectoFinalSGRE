package co.edu.uniquindio.sgre;

import co.edu.uniquindio.sgre.viewController.InicioViewController;
import co.edu.uniquindio.sgre.viewController.EventoViewController;
import co.edu.uniquindio.sgre.viewController.LoginViewController;
import co.edu.uniquindio.sgre.viewController.SGREViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SGREMain extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        this.primaryStage.setTitle("SGRE");
        Image icon = new Image(getClass().getResourceAsStream("/persistencia/imagenes/logo.jpg"));
        stage.getIcons().add(icon);
        stage.setResizable(false);
        mostrarVentanaPrincipal();
    }

    public void mostrarVentanaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/co/edu/uniquindio/sgre/sgre.fxml"));
            AnchorPane rootLayout = loader.load();
            SGREViewController sgreViewController = loader.getController();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch();
    }
}