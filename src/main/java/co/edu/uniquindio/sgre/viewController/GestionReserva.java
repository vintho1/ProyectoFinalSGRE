package co.edu.uniquindio.sgre.viewController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GestionReserva {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<?> cbEstados;

    @FXML
    private TableColumn<?, ?> columCapReserva;

    @FXML
    private TableColumn<?, ?> columEstadoReserva;

    @FXML
    private TableColumn<?, ?> columFecha;

    @FXML
    private TableColumn<?, ?> columIdReserva;

    @FXML
    private TableView<?> tablaReservas;

    @FXML
    void actualizarReservaEvent(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}
