package co.edu.uniquindio.sgre.mapping.mappers;

import co.edu.uniquindio.sgre.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.sgre.mapping.dto.EventoDto;
import co.edu.uniquindio.sgre.mapping.dto.ReservaDto;
import co.edu.uniquindio.sgre.mapping.dto.UsuarioDto;
import co.edu.uniquindio.sgre.model.Empleado;
import co.edu.uniquindio.sgre.model.Evento;
import co.edu.uniquindio.sgre.model.Reserva;
import co.edu.uniquindio.sgre.model.Usuario;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface SGREMapper {

    SGREMapper INSTANCE = Mappers.getMapper(SGREMapper.class);

    @Named("empleadoToEmpleadoDto")
    default EmpleadoDto  empleadoToEmpleadoDto(Empleado empleado){
        if ( empleado == null ) {
            return null;
        }

        String id = null;
        String nombre = null;
        String email = null;
        String contrasenia = null;
        String rol = null;

        id = empleado.getId();
        nombre = empleado.getNombre();
        email = empleado.getEmail();
        contrasenia = empleado.getContrasenia();
        rol = empleado.getRolEmpleado().toString();

        EmpleadoDto empleadoDto = new EmpleadoDto( id, nombre, email, rol, contrasenia );

        return empleadoDto;
    }

    default Empleado empleadoDtoToEmpleado(EmpleadoDto empleadoDto){
        if ( empleadoDto == null ) {
            return null;
        }

        Empleado.EmpleadoBuilder empleado = Empleado.builder();

        empleado.id( empleadoDto.id() );
        empleado.nombre( empleadoDto.nombre() );
        empleado.email( empleadoDto.email() );
        empleado.contrasenia( empleadoDto.contrasenia() );
        empleado.rol( empleadoDto.rol() );

        return empleado.build();
    }

    @IterableMapping(qualifiedByName = "empleadoToEmpleadoDto")
    List<EmpleadoDto> getEmpleadosDto(List<Empleado> listaEmpleados);

    //////////////////

    @Named("usuarioToUsuarioDto")
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);

    Usuario usuarioToUsuarioDto(UsuarioDto usuarioDto);

    @IterableMapping(qualifiedByName = "usuarioToUsuarioDto")
    List<UsuarioDto> getUsuariosDto(List<Usuario> listaUsuarios);

    ////

    @Named("eventoToEventoDto")
    EventoDto eventoToEventoDto(Evento evento);

    Evento eventoDtoToEvento(EventoDto eventoDto);

    @IterableMapping(qualifiedByName = "eventoToEventoDto")
    List<EventoDto> getEventosDto(List<Evento> listaEventos);


    ////

    @Named("reservaToReservaDto")
    ReservaDto reservaToReservaDto(Reserva reserva);

    Reserva reservaDtoToReserva(ReservaDto reservaDto);

    @IterableMapping(qualifiedByName = "reservaToReservaDto")
    List<ReservaDto> getReservasDto(List<Reserva> listaReservas);



//    @Named("mappingToEmpeladoDto")
//    EmpleadoDto mappingToEmpeladoDto(Empleado empleado);


 //   @Mapping(target = "nombreCliente", source = "cliente.nombre")
 //   @IterableMapping(qualifiedByName = "cunetaToCuentaDto")
 //   ClienteDto clienteToClienteDto(Cliente cliente);


}
