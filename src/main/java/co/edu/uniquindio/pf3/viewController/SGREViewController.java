package co.edu.uniquindio.viewController;

import co.edu.uniquindio.controller.SGREController;
import co.edu.uniquindio.controller.service.ISGREControllerService;
import javafx.fxml.FXML;

public class SGREViewController {
    ISGREControllerService sgreControllerService;

    @FXML
    void initialize() {
        sgreControllerService = new SGREController();
    }
}
