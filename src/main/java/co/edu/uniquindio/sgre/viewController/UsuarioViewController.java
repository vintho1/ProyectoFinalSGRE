package co.edu.uniquindio.sgre.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.sgre.controller.ReservaController;
import co.edu.uniquindio.sgre.controller.UsuarioController;
import co.edu.uniquindio.sgre.mapping.dto.ReservaDto;
import co.edu.uniquindio.sgre.mapping.dto.UsuarioDto;
import co.edu.uniquindio.sgre.model.Usuario;
import co.edu.uniquindio.sgre.utils.Persistencia;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class UsuarioViewController implements EstadoAplicacion {

    UsuarioController usuarioControllerService;
    ReservaController reservaControllerService;
    ObservableList<UsuarioDto> listaUsuariosDto = FXCollections.observableArrayList();
    UsuarioDto usuarioSeleccionado;

    @FXML
    public Button btnEditar;
    @FXML
    public Button btnCancelar;
    @FXML
    public Button btnGuardar;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<ReservaDto> tblReservasus;

    @FXML
    private TableColumn<ReservaDto, String> colEstado;

    @FXML
    private TableColumn<ReservaDto, String> colEvento;

    @FXML
    private TableColumn<ReservaDto, String> colFecha;

    @FXML
    private TableColumn<ReservaDto, String> colId;


    @FXML
    private TableView<UsuarioDto> tableUsuarios;

    @FXML
    private TableColumn<UsuarioDto, String> tcCedula;

    @FXML
    private TableColumn<UsuarioDto, String> tcCorreo;

    @FXML
    private TableColumn<UsuarioDto, String> tcNombre;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtContrasenia;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtUsuario;
    @FXML
    private AnchorPane ventana;

    @FXML
    void initialize() {
        usuarioControllerService = new UsuarioController();
        reservaControllerService = new ReservaController();
        intiView();

        txtContrasenia.setDisable(true);
        txtContrasenia.setVisible(false);
        txtNombre.setDisable(true);
        txtCorreo.setDisable(true);
        if (getTipoUsuario() == 0) {
            btnEditar.setVisible(false);
            tblReservasus.setVisible(true);
            tableUsuarios.setVisible(true);
            btnEliminar.setVisible(true);

        }
        if (getTipoUsuario() == 1) {
            btnEditar.setVisible(false);
            ventana.setVisible(false);
        }
        if (getTipoUsuario() == 2) {
            btnEditar.setVisible(true);
            tblReservasus.setVisible(true);
            tableUsuarios.setVisible(false);
            btnEliminar.setVisible(false);

            Optional<UsuarioDto> usuario = listaUsuariosDto.stream().filter(usuarioDto -> usuarioDto.email().equals(getUsuario())).findFirst();

            usuario.ifPresent(usuarioDto -> {
                txtCedula.setText(usuarioDto.id());
                txtNombre.setText(usuarioDto.nombre());
                txtCorreo.setText(usuarioDto.email());
                txtContrasenia.setText(usuarioDto.contrasenia());
                List<ReservaDto> us = reservaControllerService.obtenerReservasUsuario(usuarioDto.id());
                ObservableList<ReservaDto> temp = FXCollections.observableArrayList(us);
                tblReservasus.getItems().clear();
                tblReservasus.setItems(temp);

            });
        }
    }

    private void intiView() {
        initDataBinding();
        obtenerUsuarios();
        tableUsuarios.getItems().clear();
        tableUsuarios.setItems(listaUsuariosDto);
        listenerSelection();
    }

    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        tcCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().id()));
        tcCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().email()));

        colEvento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().eventoId().getNombre()));
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fecha().toString()));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().estado().toString()));
        colId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().id()));

    }

    private void obtenerUsuarios() {
        listaUsuariosDto.addAll(usuarioControllerService.obtenerUsuario());
    }

    private void listenerSelection() {
        tableUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            usuarioSeleccionado = newSelection;
            mostrarInformacionusuario(usuarioSeleccionado);
        });
    }

    @FXML
    void agregarUsuarioAction(ActionEvent event) throws IOException {
        crearusuario();
    }

    private void crearusuario() throws IOException {
        UsuarioDto usuarioDto = construirUsuarioDto();
        if (datosValidos(usuarioDto)) {
            if (usuarioControllerService.agregarUsuario(usuarioDto)) {
                listaUsuariosDto.add(usuarioDto);
                mostrarMensaje("Notificación usuario", "Usuario creado", "El usuario se ha creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposUsuario();
            } else {
                mostrarMensaje("Notificación usuario", "Usuario no creado", "El usuario no se ha creado con éxito", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Notificación usuario", "Usuario no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }
        registrarAccionesSistema("Crear usuario", 1, "se creo en usuario con id: " + usuarioDto.id() + ", nombre: " + usuarioDto.nombre());


    }

    @FXML
    void eliminarUsuarioAction(ActionEvent event) throws IOException {
        eliminarUsuario();
    }

    private void eliminarUsuario() throws IOException {
        boolean usuarioEliminado = false;
        if (usuarioSeleccionado != null) {

            Persistencia.eliminarUsuario(usuarioSeleccionado.id());
            Persistencia.eliminarUsuarioBinario(usuarioSeleccionado.id());
            Persistencia.eliminarUsuarioXML(usuarioSeleccionado.id());


            listaUsuariosDto.remove(usuarioSeleccionado);
            usuarioSeleccionado = null;
            tableUsuarios.getSelectionModel().clearSelection();
            limpiarCamposUsuario();
            mostrarMensaje("Notificación usuario", "Usuario eliminado", "El usuario se ha eliminado con éxito", Alert.AlertType.INFORMATION);
        } else {
            mostrarMensaje("Notificación usuario", "Usuario no seleccionado", "Por favor, seleccione un usuario de la lista", Alert.AlertType.WARNING);
        }
        registrarAccionesSistema("Eliminar usuario", 1, "se elimino el usuario ");
    }

    @FXML
    void actualizarUsuarioAction(ActionEvent event) throws IOException {
        boolean usuarioActualizado = false;

        Usuario usuarioActualizad = new Usuario(
                txtCedula.getText(),
                txtNombre.getText(),
                txtCorreo.getText(),
                txtContrasenia.getText()
        );

        Persistencia.actualizarUsuarioBinario(usuarioActualizad.getId(), usuarioActualizad);
        Persistencia.actualizarUsuarioXML(usuarioActualizad.getId(), usuarioActualizad);
        Persistencia.actualizarUsuarioTxt(usuarioActualizad.getId(), usuarioActualizad);

        listaUsuariosDto.removeIf(usuarioDto -> usuarioDto.id().equals(usuarioActualizad.getId()));

        listaUsuariosDto.add(new UsuarioDto(
                usuarioActualizad.getId(),
                usuarioActualizad.getNombre(),
                usuarioActualizad.getEmail(),
                usuarioActualizad.getContrasenia()
        ));
        tableUsuarios.refresh();

        mostrarMensaje("Notificación usuario", "Usuario actualizado", "El usuario se ha actualizado con éxito", Alert.AlertType.INFORMATION);

        limpiarCamposUsuario();
        txtNombre.setDisable(true);
        txtCorreo.setDisable(true);

        registrarAccionesSistema("Actualizar usuario", 1, "Se actualizó el usuario " + usuarioActualizado);
    }

    @FXML
    public void editarAction(ActionEvent actionEvent) {
        btnGuardar.setVisible(true);
        btnCancelar.setVisible(true);
        txtNombre.setDisable(false);
        txtCorreo.setDisable(false);
    }

    @FXML
    public void cancelarAction(ActionEvent actionEvent) {
        btnGuardar.setVisible(false);
        btnCancelar.setVisible(false);
        txtNombre.setDisable(true);
        txtCorreo.setDisable(true);
    }

    @FXML
    void nuevoUsuarioAction(ActionEvent event) {
        limpiarCamposUsuario();
        establecerPromptText();
    }

    private void establecerPromptText() {
        txtNombre.setPromptText("Ingrese el nombre");
        txtCedula.setPromptText("Ingrese la cedula");
        txtCorreo.setPromptText("Ingrese el correo");
        txtUsuario.setPromptText("Ingrese el usuario");
        txtCorreo.setPromptText("Ingrese el contraseña");

    }


    private UsuarioDto construirUsuarioDto() {
        return new UsuarioDto(
                txtCedula.getText(),
                txtNombre.getText(),
                txtCorreo.getText(),
                txtContrasenia.getText()
        );
    }

    private void limpiarCamposUsuario() {
        txtNombre.setText("");
        txtCedula.setText("");
        txtCorreo.setText("");
        txtContrasenia.setText("");
    }

    private boolean datosValidos(UsuarioDto usuarioDto) {
        String mensaje = "";
        if (usuarioDto.nombre() == null || usuarioDto.nombre().equals(""))
            mensaje += "El nombre es obligatorio \n";
        if (usuarioDto.id() == null || usuarioDto.id().equals(""))
            mensaje += "El id es obligatorio \n";
        if (usuarioDto.email() == null || usuarioDto.email().equals(""))
            mensaje += "El email es obligatorio \n";
        if (mensaje.equals("")) {
            return true;
        } else {
            mostrarMensaje("Notificación cliente", "Datos invalidos", mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    private void mostrarInformacionusuario(UsuarioDto usuarioSeleccionado) {
        if (usuarioSeleccionado != null) {
            txtNombre.setText(usuarioSeleccionado.nombre());
            txtCedula.setText(usuarioSeleccionado.id());
            txtCorreo.setText(usuarioSeleccionado.email());
            txtContrasenia.setText(usuarioSeleccionado.contrasenia());

        }
    }

    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }

    @Override
    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                Alert aler = new Alert(alertType);
                aler.setTitle(titulo);
                aler.setHeaderText(header);
                aler.setContentText(contenido);
                aler.showAndWait();
            }
        });
    }
}
