package co.edu.uniquindio.parfin.controller;

import co.edu.uniquindio.parfin.controller.service.ISGREControllerService;

public class SGREController implements ISGREControllerService {

    ModelFactoryController modelFactoryController;

    public SGREController(){
        System.out.println("Llamando al singleton desde SGREServiceController");
        modelFactoryController = ModelFactoryController.getInstance();
    }

}
