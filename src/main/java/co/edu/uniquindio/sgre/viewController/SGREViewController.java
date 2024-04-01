package co.edu.uniquindio.sgre.viewController;

import co.edu.uniquindio.sgre.controller.SGREController;
import co.edu.uniquindio.sgre.controller.service.ISGREControllerService;
import javafx.fxml.FXML;

public class SGREViewController {
    ISGREControllerService sgreControllerService;

    @FXML
    void initialize() {
        sgreControllerService = new SGREController();
    }
}
