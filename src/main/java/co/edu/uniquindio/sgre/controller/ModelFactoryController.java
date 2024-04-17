package co.edu.uniquindio.sgre.controller;

import co.edu.uniquindio.sgre.controller.service.IModelFactoryController;
import co.edu.uniquindio.sgre.exceptions.EmpleadoException;
import co.edu.uniquindio.sgre.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.sgre.mapping.dto.UsuarioDto;
import co.edu.uniquindio.sgre.mapping.mappers.SGREMapper;
import co.edu.uniquindio.sgre.model.Empleado;
import co.edu.uniquindio.sgre.model.SGRE;
import co.edu.uniquindio.sgre.utils.Persistencia;
import co.edu.uniquindio.sgre.utils.SGREUtils;
//import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModelFactoryController implements IModelFactoryController {

    public void setMapper(SGREMapper mapper) {
        this.mapper = mapper;
    }

    SGRE sgre;
    SGREMapper mapper = SGREMapper.INSTANCE;

    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    // Método para obtener la instancia de nuestra clase
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        //1. inicializar datos y luego guardarlo en archivos
        System.out.println("invocación clase singleton");
     //  cargarDatosBase();
      // salvarDatosPrueba();

        //2. Cargar los datos de los archivos
	//	cargarDatosDesdeArchivos();

        //3. Guardar y Cargar el recurso serializable binario
	cargarResourceBinario();
	//	guardarResourceBinario();

        //4. Guardar y Cargar el recurso serializable XML
        //   cargarResourceXML();
		guardarResourceXML();


        //Siempre se debe verificar si la raiz del recurso es null

        if(sgre == null){
            cargarDatosBase();
         //  guardarResourceXML();
        }
        registrarAccionesSistema("Inicio de sesión", 1, "inicioSesión");
    }
    private void cargarDatosDesdeArchivos() {
        sgre = new SGRE();
        try {
            Persistencia.cargarDatosArchivos(sgre);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void salvarDatosPrueba() {
        try {
            Persistencia.guardarEmpleados(getSGRE().getListaEmpleados());
           // Persistencia.guardarClientes(getSGRE().getListaClientes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    private void cargarDatosBase() {
        sgre = SGREUtils.inicializarDatos();
    }

    public SGRE getSGRE() {
        return sgre;
    }

    public void setSgre(SGRE sgre) {
        this.sgre = sgre;
    }

    @Override
    public List<EmpleadoDto> obtenerEmpleados() {
        return  mapper.getEmpleadosDto(sgre.getListaEmpleados());
    }

    @Override
    public boolean agregarEmpleado(EmpleadoDto empleadoDto) {
        try{
            if(!sgre.verificarEmpleadoExistente(empleadoDto.id())) {
                Empleado empleado = mapper.empleadoDtoToEmpleado(empleadoDto);
                getSGRE().agregarEmpleado(empleado);
                guardarResourceBinario();
                guardarResourceXML();
                guardarListaEmpleados(getSGRE().getListaEmpleados());
            }
            return true;
        }catch (EmpleadoException e){
            e.getMessage();
            return false;
        }catch (IOException e2){
            e2.getMessage();
            return false;
        }
    }

    @Override
    public boolean eliminarEmpleado(String cedula) {
        boolean flagExiste = false;
        try {
            flagExiste = getSGRE().eliminarEmpleado(cedula);
            guardarResourceBinario();
            guardarResourceXML();
        } catch (EmpleadoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return flagExiste;
    }

    @Override
    public boolean actualizarEmpleado(String cedulaActual, EmpleadoDto empleadoDto) {
        try {
            Empleado empleado = mapper.empleadoDtoToEmpleado(empleadoDto);
            getSGRE().actualizarEmpleado(cedulaActual, empleado);
            guardarResourceBinario();
            guardarResourceXML();
            return true;
        } catch (EmpleadoException e) {
            e.printStackTrace();
            return false;
        }
    }
    private void cargarResourceXML() {
        sgre = Persistencia.cargarRecursoBancoXML();
    }

    private void guardarResourceXML() {
        Persistencia.guardarRecursoBancoXML(sgre);
    }

    private void cargarResourceBinario() {
        sgre = Persistencia.cargarRecursoBancoBinario();
    }

    private void guardarResourceBinario() {
        Persistencia.guardarRecursoBancoBinario(sgre);
    }

    private void guardarListaEmpleados(ArrayList<Empleado> listaEmpleados) throws  IOException{
        Persistencia.guardarEmpleados(listaEmpleados);
    }
    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }


    // Usuario




}
