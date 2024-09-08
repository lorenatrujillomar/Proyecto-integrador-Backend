package proyecto.integrador.clinica.service;

import proyecto.integrador.clinica.dto.request.OdontologoModificarDto;
import proyecto.integrador.clinica.dto.request.OdontologoRequestDto;
import proyecto.integrador.clinica.dto.response.OdontologoResponseDto;
import proyecto.integrador.clinica.entity.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    OdontologoResponseDto guardarOdontologo(OdontologoRequestDto odontologoRequestDto);
    Optional<Odontologo> buscarPorId(Integer id);
    List<OdontologoResponseDto> buscarTodos();
    void modificarOdontologo(OdontologoModificarDto odontologoModificarDto);
    void eliminarOdontologo(Integer id);
    List<Odontologo> buscarPorUnaParteNumeroMatricula(String matricula);
}
