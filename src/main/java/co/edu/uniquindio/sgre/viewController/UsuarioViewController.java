package co.edu.uniquindio.sgre.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.sgre.controller.UsuarioController;
import co.edu.uniquindio.sgre.mapping.dto.UsuarioDto;
import co.edu.uniquindio.sgre.model.Usuario;
import co.edu.uniquindio.sgre.utils.Persistencia;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UsuarioViewController {
    UsuarioController usuarioControllerService;
    ObservableList<UsuarioDto> listaUsuariosDto = FXCollections.observableArrayList();
    UsuarioDto usuarioSeleccionado;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnNuevo;

    @FXML
    private TableView<UsuarioDto> tableUsuarios;

    @FXML
    private TableColumn<UsuarioDto,String> tcCedula;

    @FXML
    private TableColumn<UsuarioDto,String> tcCorreo;

    @FXML
    private TableColumn<UsuarioDto,String> tcNombre;

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
    void initialize() {
        usuarioControllerService = new UsuarioController();
        intiView();
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

    }

    private void obtenerUsuarios() {
        listaUsuariosDto.addAll(usuarioControllerService.obtenerUsuario());
    }

    private void listenerSelection() {
        tableUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            usuarioSeleccionado = newSelection;
            mostrarInformacionEmpleado(usuarioSeleccionado);
        });
    }
    @FXML
    void agregarUsuarioAction(ActionEvent event) {
        crearEmpleado();
    }

    private void crearEmpleado() {
        UsuarioDto usuarioDto = construirUsuarioDto();
        if(datosValidos(usuarioDto)){
            if(usuarioControllerService.agregarUsuario(usuarioDto)){
                listaUsuariosDto.add(usuarioDto);
                mostrarMensaje("Notificación empleado", "Usuario creado", "El empleado se ha creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposUsuario();
            }else{
                mostrarMensaje("Notificación empleado", "Usuario no creado", "El empleado no se ha creado con éxito", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Notificación empleado", "Usuario no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }
        registrarAccionesSistema("Crear empleado", 1, "se creo en empleado "+usuarioDto);
    }

    @FXML
    void eliminarUsuarioAction(ActionEvent event) throws IOException {
        eliminarUsuario();
    }
    private void eliminarUsuario() throws IOException {
        boolean empleadoEliminado = false;
        if (usuarioSeleccionado != null) {

            Persistencia.eliminarUsuario(usuarioSeleccionado.id());
            Persistencia.eliminarUsuarioBinario(usuarioSeleccionado.id());
            Persistencia.eliminarUsuarioXML(usuarioSeleccionado.id());



            listaUsuariosDto.remove(usuarioSeleccionado);
            usuarioSeleccionado = null;
            tableUsuarios.getSelectionModel().clearSelection();
            limpiarCamposUsuario();
            mostrarMensaje("Notificación empleado", "Usuario eliminado", "El empleado se ha eliminado con éxito", Alert.AlertType.INFORMATION);
        } else {
            mostrarMensaje("Notificación empleado", "Usuario no seleccionado", "Por favor, seleccione un empleado de la lista", Alert.AlertType.WARNING);
        }
        registrarAccionesSistema("Eliminar empleado", 1, "se elimino el empleado ");
    }

    @FXML
    void actualizarUsuarioAction(ActionEvent event) throws IOException {
        actualizarUsuario();
    }
    private void actualizarUsuario() throws IOException {
        boolean empleadoActualizado = false;

        Usuario usuarioActualizad = new Usuario(
                txtCedula.getText(),
                txtNombre.getText(),
                txtCorreo.getText(),
                txtUsuario.getText(),
                txtContrasenia.getText()
        );

        if (usuarioSeleccionado != null) {

                            Persistencia.actualizarUsuarioBinario(usuarioSeleccionado.id(), usuarioActualizad);
                            Persistencia.actualizarUsuarioXML(usuarioSeleccionado.id(), usuarioActualizad);
                            Persistencia.actualizarUsuarioTxt(usuarioSeleccionado.id(),usuarioActualizad);



            listaUsuariosDto.remove(usuarioSeleccionado);

                listaUsuariosDto.add(new UsuarioDto(
                        usuarioActualizad.getId(),
                        usuarioActualizad.getNombre(),
                        usuarioActualizad.getEmail(),
                        usuarioActualizad.getUsuario(),
                        usuarioActualizad.getContrasenia()
                ));


            tableUsuarios.refresh();

            mostrarMensaje("Notificación empleado", "Usuario actualizado", "El empleado se ha actualizado con éxito", Alert.AlertType.INFORMATION);

            limpiarCamposUsuario();
        } else {

            mostrarMensaje("Notificación empleado", "Usuario no seleccionado", "Por favor, seleccione un empleado de la lista", Alert.AlertType.WARNING);
        }

        registrarAccionesSistema("Actualizar empleado", 1, "Se actualizó el empleado " + empleadoActualizado);
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
                txtUsuario.getText(),
                txtContrasenia.getText()

        );
    }
    private void limpiarCamposUsuario() {
        txtNombre.setText("");
        txtCedula.setText("");
        txtCorreo.setText("");
        txtUsuario.setText("");
        txtContrasenia.setText("");
    }

    private boolean datosValidos(UsuarioDto usuarioDto) {
        String mensaje = "";
        if(usuarioDto.nombre() == null || usuarioDto.nombre().equals(""))
            mensaje += "El nombre es obligatorio \n" ;
        if(usuarioDto.id() == null || usuarioDto.id() .equals(""))
            mensaje += "El id es obligatorio \n" ;
        if(usuarioDto.email() == null || usuarioDto.email().equals(""))
            mensaje += "El email es obligatorio \n" ;
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
    private void mostrarInformacionEmpleado(UsuarioDto usuarioSeleccionado) {
        if(usuarioSeleccionado != null){
            txtNombre.setText(usuarioSeleccionado.nombre());
            txtCedula.setText(usuarioSeleccionado.id());
            txtCorreo.setText(usuarioSeleccionado.email());
            txtUsuario.setText(usuarioSeleccionado.usuario());
            txtContrasenia.setText(usuarioSeleccionado.contrasenia());

        }
    }
    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }
    
    
    

}