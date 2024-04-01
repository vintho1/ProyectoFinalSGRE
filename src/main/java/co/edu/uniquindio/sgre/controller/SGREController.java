package co.edu.uniquindio.sgre.controller;

import co.edu.uniquindio.sgre.controller.service.ISGREControllerService;

public class SGREController implements ISGREControllerService {

    ModelFactoryController modelFactoryController;

    public SGREController(){
        System.out.println("Llamando al singleton desde SGREServiceController");
        modelFactoryController = ModelFactoryController.getInstance();
    }

}
