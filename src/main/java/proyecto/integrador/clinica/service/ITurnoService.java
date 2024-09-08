package proyecto.integrador.clinica.service;

import proyecto.integrador.clinica.dto.request.TurnoModificarDto;
import proyecto.integrador.clinica.dto.request.TurnoRequestDto;
import proyecto.integrador.clinica.dto.response.TurnoResponseDto;
import proyecto.integrador.clinica.entity.Turno;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    TurnoResponseDto guardarTurno(TurnoRequestDto turnoRequestDto);
    Optional<Turno> buscarPorId(Integer id);
    List<TurnoResponseDto> buscarTodos();
    void modificarTurnos(TurnoModificarDto turnoModificarDto);
    void eliminarTurno(Integer id);
}
