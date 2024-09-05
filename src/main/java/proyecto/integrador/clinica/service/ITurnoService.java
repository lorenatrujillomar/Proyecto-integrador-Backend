package proyecto.integrador.clinica.service;

import proyecto.integrador.clinica.entity.Turno;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    Turno guardarTurno(Turno turno);
    Optional<Turno> buscarPorId(Integer id);
    List<Turno> buscarTodos();
    void modificarTurnos(Turno turno);
    void eliminarTurno(Integer id);
}
