package co.edu.uniquindio.mapping.mappers;

import co.edu.uniquindio.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.model.Empleado;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface SGREMapper {

    SGREMapper INSTANCE = Mappers.getMapper(SGREMapper.class);

    @Named("empleadoToEmpleadoDto")
    EmpleadoDto empleadoToEmpleadoDto(Empleado empleado);

    Empleado empleadoDtoToEmpleado(EmpleadoDto empleadoDto);

    @IterableMapping(qualifiedByName = "empleadoToEmpleadoDto")
    List<EmpleadoDto> getEmpleadosDto(List<Empleado> listaEmpleados);

//    @Named("mappingToEmpeladoDto")
//    EmpleadoDto mappingToEmpeladoDto(Empleado empleado);


 //   @Mapping(target = "nombreCliente", source = "cliente.nombre")
 //   @IterableMapping(qualifiedByName = "cunetaToCuentaDto")
 //   ClienteDto clienteToClienteDto(Cliente cliente);


}
