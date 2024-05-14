package co.edu.uniquindio.sgre.viewController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RegistroEventoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> columCapEvento;

    @FXML
    private TableColumn<?, ?> columIdEmpleado;

    @FXML
    private TableColumn<?, ?> columIdEvento;

    @FXML
    private TableColumn<?, ?> columNombreEmpleado;

    @FXML
    private TableColumn<?, ?> columNombreEvento;

    @FXML
    private DatePicker dateFecha;

    @FXML
    private TableView<?> tablaEmpleados;

    @FXML
    private TableView<?> tablaEventos;

    @FXML
    private TextField txtCapMax;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtEmpleado;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    @FXML
    private AnchorPane ventana;

    @FXML
    void ActualizarEvent(ActionEvent event) {

    }

    @FXML
    void CancelarEvent(ActionEvent event) {

    }

    @FXML
    void EliminarEvent(ActionEvent event) {

    }

    @FXML
    void agregarEvent(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}
