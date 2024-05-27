package co.edu.uniquindio.sgre.viewController;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

import co.edu.uniquindio.sgre.controller.EmpleadoController;
import co.edu.uniquindio.sgre.controller.EventoController;
import co.edu.uniquindio.sgre.controller.ReservaController;
import co.edu.uniquindio.sgre.controller.UsuarioController;
import co.edu.uniquindio.sgre.exceptions.EmpleadoException;
import co.edu.uniquindio.sgre.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.sgre.mapping.dto.EventoDto;
import co.edu.uniquindio.sgre.mapping.dto.ReservaDto;
import co.edu.uniquindio.sgre.mapping.dto.UsuarioDto;
import co.edu.uniquindio.sgre.model.*;
import co.edu.uniquindio.sgre.utils.Persistencia;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import static co.edu.uniquindio.sgre.SGREMain.*;

public class ReservaViewController implements EstadoAplicacion {

    @FXML
    public GridPane creacionGridPane;
    @FXML
    public Button btnReservar;
    @FXML
    public Button btnActualizar;
    @FXML
    public TextField txtId;
    @FXML
    public ComboBox<String> txtEstado;

    EventoController eventoControllerService;
    ObservableList<EventoDto> listaEventosDto = FXCollections.observableArrayList();
    EventoDto eventoSeleccionado;

    //////

    ReservaController reservaControllerService;
    ObservableList<ReservaDto> listaReservaDto = FXCollections.observableArrayList();
    ReservaDto reservaSeleccionado;


    ///////

    UsuarioController UsuarioControllerService;
    ObservableList<UsuarioDto> listaUsuarioDto = FXCollections.observableArrayList();

    private UsuarioDto usuarioLogueado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<EventoDto, String> columCapEvento;

    @FXML
    private TableColumn<ReservaDto, String> columCapReserva;

    @FXML
    private TableColumn<ReservaDto, Estado> columEstadoReserva;

    @FXML
    private TableColumn<EventoDto, String> columIdEvento;

    @FXML
    private TableColumn<ReservaDto, String> columIdReserva;

    @FXML
    private TableColumn<EventoDto, String> columNombreEvento;

    @FXML
    private TableColumn<ReservaDto, LocalDate> columFecha;

    @FXML
    private DatePicker dateFecha;

    @FXML
    private TableView<EventoDto> tablaEventos;

    @FXML
    private TableView<ReservaDto> tablaReservas;

    @FXML
    private TextField txtcapacidad;
    @FXML
    private TextField txtIdUsuario;

    @FXML
    void reservaEvent(ActionEvent event) throws EmpleadoException {

        ReservaDto reservaDto = construirReservaDto();
        if (datosValidos(reservaDto)) {
            if (reservaControllerService.agregarReserva(reservaDto)) {
                listaReservaDto.add(reservaDto);
                mostrarMensaje("Notificación reserva", "Reserva creada", "La reserva se ha creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposReserva();
            } else {
                mostrarMensaje("Notificación reserva", "Reserva no creada", "La reserva no se ha creado", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Notificación reserva", "Reserva no creada", "Los datos ingresados son inválidos", Alert.AlertType.ERROR);
        }
        registrarAccionesSistema("Crear reserva", 1, "se creó la reserva " + reservaDto);
        try {
            channel.basicPublish(COLA_SOLICITUD_RESERVA, "", null, "Solicitud de reserva".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean datosValidos(ReservaDto eventoDto) {
        String mensaje = "";
        if (eventoDto.capacidad() == null || eventoDto.capacidad().equals(""))
            mensaje += "La capacidad es obligatoria \n";
        if (eventoDto.fecha() == null || LocalDate.parse(eventoDto.fecha()).isBefore(LocalDate.now().plusDays(1)))
            mensaje += "La fecha debe ser posterior al día actual \n";
        if (eventoDto.eventoId() == null)
            mensaje += "Debe seleccionar un evento \n";
        if (mensaje.equals("")) {
            return true;
        } else {
            mostrarMensaje("Notificación evento", "Datos inválidos", mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    @FXML
    void cancelarEvent(ActionEvent event) {

    }

    @FXML
    void actualizarReserva(ActionEvent event) {
        boolean usuarioActualizado = false;

        if (eventoSeleccionado == null) {
            mostrarMensaje("Notificación reserva", "Reserva creada", "La reserva se ha creado con éxito", Alert.AlertType.INFORMATION);
            return;
        }

        Reserva reservaActualizad = new Reserva(
                txtId.getText(),
                new Usuario(getUsuario(), "", "", ""),
                new Evento(eventoSeleccionado.id(), eventoSeleccionado.nombre(), eventoSeleccionado.descripcion(), eventoSeleccionado.fecha(), Integer.parseInt(eventoSeleccionado.capMax()), null),
                dateFecha.getValue().toString(),
                Estado.valueOf(txtEstado.getValue())
        );

        try {
            Persistencia.actualizarReserva(reservaActualizad.getId(), reservaActualizad);

            listaReservaDto.removeIf(usuarioDto -> usuarioDto.id().equals(reservaActualizad.getId()));

            listaReservaDto.add(new ReservaDto(
                    reservaActualizad.getId(),
                    txtcapacidad.getText(),
                    reservaActualizad.getUsuario().getId(),
                    reservaActualizad.getEvento(),
                    reservaActualizad.getFecha(),
                    reservaActualizad.getEstado()
            ));
            tablaReservas.refresh();

            mostrarMensaje("Notificación reserva", "Reserva creada", "La reserva se ha creado con éxito", Alert.AlertType.INFORMATION);

            limpiarCamposReserva();

            registrarAccionesSistema("Actualizar usuario", 1, "Se actualizó el usuario " + usuarioActualizado);

            intiView();


            try {
                channel.basicPublish(COLA_RESERVA_VALIDADA, reservaActualizad.getUsuario().getEmail(), null, "Solicitud de reserva Actualizada:".getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        eventoControllerService = new EventoController();
        reservaControllerService = new ReservaController();
        intiView();
        txtIdUsuario.setDisable(true);
        if (getTipoUsuario() == 0) {
            txtIdUsuario.setText(getUsuario());
            creacionGridPane.setVisible(false);
            btnReservar.setVisible(false);
            btnActualizar.setVisible(false);
        }
        if (getTipoUsuario() == 1) {
            txtIdUsuario.setDisable(true);
            txtIdUsuario.setText(getUsuario());
            creacionGridPane.setVisible(true);
            btnReservar.setVisible(false);
            btnActualizar.setVisible(true);
            txtcapacidad.setDisable(true);
            dateFecha.setValue(null);
        }
        if (getTipoUsuario() == 2) {
            txtIdUsuario.setDisable(true);
            txtIdUsuario.setText(getUsuario());
            btnReservar.setVisible(true);
            btnActualizar.setVisible(false);
            txtcapacidad.setDisable(false);
        }
    }

    private void intiView() {
        initDataBinding();
        obtenerReservas();
        obtenerEvento();
        obtenerEventoDisponibles();
        tablaEventos.getItems().clear();
        tablaEventos.setItems(listaEventosDto);
        tablaReservas.getItems().clear();
        tablaReservas.setItems(listaReservaDto);
        listenerSelection();
        ObservableList<String> combo = FXCollections.observableArrayList();
        combo.add(Estado.APROBADA.name());
        combo.add(Estado.PENDIENTE.name());
        combo.add(Estado.RECHAZADA.name());
        txtEstado.setItems(combo);
    }

    private void obtenerEventoDisponibles() {
        listaEventosDto.clear();
        ObservableList<EventoDto> todosEventos = FXCollections.observableArrayList(eventoControllerService.obtenerEventos());
        listaEventosDto.addAll(todosEventos);
    }

    private void initDataBinding() {
        columNombreEvento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        columIdEvento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().id()));
        columCapEvento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().capMax()));

        columCapReserva.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().capacidad()));
        columEstadoReserva.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().estado()));
        columIdReserva.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().id()));
        columFecha.setCellValueFactory(cellData -> new SimpleObjectProperty<>(LocalDate.parse(cellData.getValue().fecha())));
    }

    private void obtenerReservas() {
        listaReservaDto.clear();
        List<ReservaDto> todasReservas = reservaControllerService.obtenerReservas();
        listaReservaDto.addAll(todasReservas);
    }


    private void obtenerEvento() {
        listaEventosDto.addAll(eventoControllerService.obtenerEventos());
    }

    private void listenerSelection() {

        tablaReservas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            reservaSeleccionado = newSelection;
            mostrarInformacionEvento(reservaSeleccionado);
        });
        tablaEventos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            eventoSeleccionado = newSelection;
        });

    }

    private ReservaDto construirReservaDto() {
        return new ReservaDto(
                UUID.randomUUID().toString(),
                txtcapacidad.getText(),
                txtIdUsuario.getText(),
                eventoSeleccionado != null ? new Evento(eventoSeleccionado.id(), eventoSeleccionado.nombre(), eventoSeleccionado.capMax()) : null,
                dateFecha.getValue().toString(),
                Estado.PENDIENTE
        );
    }

    private void limpiarCamposReserva() {

        txtIdUsuario.setText("");
        txtcapacidad.setText("");
        dateFecha.setValue(null);
        registrarAccionesSistema("Limpiar campos de la reserva", 1, "se limpiaron los campos");
    }

    private void mostrarInformacionEvento(ReservaDto reservaSeleccionado) {
        if (reservaSeleccionado != null) {
            txtId.setText(reservaSeleccionado.id());
            txtcapacidad.setText(reservaSeleccionado.capacidad());
            txtIdUsuario.setText(reservaSeleccionado.usuarioId());
            dateFecha.setValue(LocalDate.parse(reservaSeleccionado.fecha()));
        }
    }

    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }


}
