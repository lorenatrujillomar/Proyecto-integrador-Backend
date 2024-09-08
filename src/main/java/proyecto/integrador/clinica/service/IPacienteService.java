package proyecto.integrador.clinica.service;

import proyecto.integrador.clinica.dto.request.PacienteModificarDto;
import proyecto.integrador.clinica.dto.request.PacienteRequestDto;
import proyecto.integrador.clinica.dto.response.PacienteResponseDto;
import proyecto.integrador.clinica.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    PacienteResponseDto guardarPaciente(PacienteRequestDto pacienteRequestDto);
    Optional<Paciente> buscarPorId(Integer id);
    List<PacienteResponseDto> buscarTodos();
    void modificarPaciente(PacienteModificarDto pacienteModificarDto);
    void eliminarPaciente(Integer id);
}
