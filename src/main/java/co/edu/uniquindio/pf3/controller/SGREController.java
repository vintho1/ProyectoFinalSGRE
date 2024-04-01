package co.edu.uniquindio.controller;

import co.edu.uniquindio.controller.service.ISGREControllerService;

public class SGREController implements ISGREControllerService {

    ModelFactoryController modelFactoryController;

    public SGREController(){
        System.out.println("Llamando al singleton desde BancoServiceController");
        modelFactoryController = ModelFactoryController.getInstance();
    }

}
