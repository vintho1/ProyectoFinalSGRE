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
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface SGREMapper {

    SGREMapper INSTANCE = Mappers.getMapper(SGREMapper.class);

    @Named("empleadoToEmpleadoDto")
    EmpleadoDto empleadoToEmpleadoDto(Empleado empleado);

    Empleado empleadoDtoToEmpleado(EmpleadoDto empleadoDto);

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
