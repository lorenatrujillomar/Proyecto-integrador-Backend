package proyecto.integrador.clinica.service;

import proyecto.integrador.clinica.dto.request.TurnoModificarDto;
import proyecto.integrador.clinica.dto.request.TurnoRequestDto;
import proyecto.integrador.clinica.dto.response.TurnoResponseDto;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    TurnoResponseDto guardarTurno(TurnoRequestDto turnoRequestDto);
    Optional<TurnoResponseDto> buscarPorId(Integer id);
    List<TurnoResponseDto> buscarTodos();
    void modificarTurnos(TurnoModificarDto turnoModificarDto);
    void eliminarTurno(Integer id);
    Optional<TurnoResponseDto> buscarTurnosPorPaciente(String pacienteApellido);
    Optional<TurnoResponseDto> buscarporDniPaciente(String pacienteDni);
}
