package co.edu.uniquindio.sgre.viewController;

import co.edu.uniquindio.sgre.SGREMain;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ViewController implements EstadoAplicacion {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public ViewController(AnchorPane currentAnchorPane, String fxml) throws IOException {
        AnchorPane nextAnchorPane = FXMLLoader.load(Objects.requireNonNull(SGREMain.class.getResource(fxml)));

        currentAnchorPane.getChildren().removeAll();
        currentAnchorPane.getChildren().setAll(nextAnchorPane);
    }

}