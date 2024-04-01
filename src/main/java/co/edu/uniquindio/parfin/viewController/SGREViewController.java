package co.edu.uniquindio.parfin.viewController;

import co.edu.uniquindio.parfin.controller.SGREController;
import co.edu.uniquindio.parfin.controller.service.ISGREControllerService;
import javafx.fxml.FXML;

public class SGREViewController {
    ISGREControllerService sgreControllerService;

    @FXML
    void initialize() {
        sgreControllerService = new SGREController();
    }
}
