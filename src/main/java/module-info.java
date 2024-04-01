module co.edu.uniquindio.parfin {
    requires javafx.controls;
    requires javafx.fxml;
    // requires lombok;
    requires org.mapstruct;
    requires org.controlsfx.controls;

    opens co.edu.uniquindio.parfin to javafx.fxml;
    exports co.edu.uniquindio.parfin;
    exports co.edu.uniquindio.parfin.viewController;
    opens co.edu.uniquindio.parfin.viewController to javafx.fxml;
    opens co.edu.uniquindio.parfin.mapping.mappers;
    exports co.edu.uniquindio.parfin.controller;
    exports co.edu.uniquindio.parfin.mapping.dto;
    exports co.edu.uniquindio.parfin.mapping.mappers;
    exports co.edu.uniquindio.parfin.model;
    opens co.edu.uniquindio.parfin.controller to javafx.fxml;
}
