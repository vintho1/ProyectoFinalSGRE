package co.edu.uniquindio.sgre.viewController;

import co.edu.uniquindio.sgre.controller.SGREController;
import co.edu.uniquindio.sgre.controller.service.ISGREControllerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class SGREViewController {
    ISGREControllerService sgreControllerService;
    @FXML
    private AnchorPane ventana;

    @FXML
    void PerfilEvent(ActionEvent event) {

    }


    @FXML
    void initialize() {
        sgreControllerService = new SGREController();
    }
}