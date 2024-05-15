package co.edu.uniquindio.sgre.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.sgre.controller.EmpleadoController;
import co.edu.uniquindio.sgre.controller.EventoController;
import co.edu.uniquindio.sgre.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.sgre.mapping.dto.EventoDto;
import co.edu.uniquindio.sgre.model.Empleado;
import co.edu.uniquindio.sgre.model.Evento;
import co.edu.uniquindio.sgre.utils.Persistencia;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class RegistroEventoViewController {

    EventoController eventoControllerService;
    ObservableList<EventoDto> listaEventosDto = FXCollections.observableArrayList();
    EventoDto eventoSeleccionado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<EventoDto, String> columCapEvento;

    @FXML
    private TableColumn<EmpleadoDto, String> columIdEmpleado;

    @FXML
    private TableColumn<EventoDto, String> columIdEvento;

    @FXML
    private TableColumn<EmpleadoDto, String> columNombreEmpleado;

    @FXML
    private TableColumn<EventoDto, String> columNombreEvento;

    @FXML
    private DatePicker dateFecha;

    @FXML
    private TableView<EmpleadoDto> tablaEmpleados;

    @FXML
    private TableView<EventoDto> tablaEventos;

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
    void initialize() {
        eventoControllerService = new EventoController();
        intiView();
    }


    private void intiView() {
        initDataBinding();
        obtenerEvento();
        tablaEventos.getItems().clear();
        tablaEventos.setItems(listaEventosDto);
        listenerSelection();
    }

    private void initDataBinding() {


        columNombreEvento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        columIdEvento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().id()));
        columCapEvento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().capMax()));
       // columNombreEmpleado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));


    }
    private void obtenerEvento() {
        listaEventosDto.addAll(eventoControllerService.obtenerEventos());
    }

    private void listenerSelection() {
        tablaEventos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            eventoSeleccionado = newSelection;
            mostrarInformacionEmpleado(eventoSeleccionado);
        });
    }
    @FXML
    void agregarEvent(ActionEvent event) {
        agregarEventAction();

    }

    private void agregarEventAction() {
        EventoDto eventoDto = construirEventoDto();
        if(datosValidos(eventoDto)){
            if(eventoControllerService.agregarEvento(eventoDto)){
                listaEventosDto.add(eventoDto);
                mostrarMensaje("Notificación evento", "Evento creado", "El evento se ha creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposEmpleado();
            }else{
                mostrarMensaje("Notificación evento", "Evento no creado", "El evento no se ha creado ", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Notificación evento", "Evento no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }
        registrarAccionesSistema("Crear evento", 1, "se creo el evento "+eventoDto);
    }
    private boolean datosValidos(EventoDto eventoDto) {
        String mensaje = "";
        if(eventoDto.nombre() == null || eventoDto.nombre().equals(""))
            mensaje += "El nombre es invalido \n" ;
        if(eventoDto.id() == null || eventoDto.id() .equals(""))
            mensaje += "El id es invalido \n" ;
        if(eventoDto.descripcion() == null || eventoDto.descripcion().equals(""))
            mensaje += "El email es invalido \n" ;
        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificación cliente","Datos invalidos",mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    private EventoDto construirEventoDto() {
        return new EventoDto(
                txtId.getText(),
                txtNombre.getText(),
                txtDescripcion.getText(),
                dateFecha.getValue(),
                txtCapMax.getText(),
                txtEmpleado.getText()

        );
    }
    private void limpiarCamposEmpleado() {
        txtNombre.setText("");
        txtId.setText("");
        txtDescripcion.setText("");
        txtCapMax.setText("");
        txtEmpleado.setText("");
    }
    @FXML
    void ActualizarEvent(ActionEvent event) {
        actualizarEventoAction();

    }

    private void actualizarEventoAction() {
    }

    @FXML
    void CancelarEvent(ActionEvent event) {
        cancelarEventoAction();

    }



    @FXML
    void EliminarEvent(ActionEvent event) {
        eliminarEvent();

    }

    private void eliminarEvent() {
    }
    private void cancelarEventoAction() {
        cancelarEventoActio();
    }

    private void cancelarEventoActio() {
    }

    private void mostrarInformacionEmpleado(EventoDto empleadoSeleccionado) {
        if(empleadoSeleccionado != null){
            txtNombre.setText(empleadoSeleccionado.nombre());
            txtId.setText(empleadoSeleccionado.id());
            txtCapMax.setText(empleadoSeleccionado.capMax());
            txtCapMax.setText(empleadoSeleccionado.capMax());


        }
    }
    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }
    

}
