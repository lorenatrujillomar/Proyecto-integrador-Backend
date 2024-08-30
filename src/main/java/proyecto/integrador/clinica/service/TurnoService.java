package proyecto.integrador.clinica.service;

import org.springframework.stereotype.Service;
import proyecto.integrador.clinica.dao.IDao;
import proyecto.integrador.clinica.model.Odontologo;
import proyecto.integrador.clinica.model.Paciente;
import proyecto.integrador.clinica.model.Turno;

import java.util.List;

@Service
public class TurnoService {
    private IDao<Turno> turnoIDao;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    public TurnoService(IDao<Turno> turnoIDao, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoIDao = turnoIDao;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    public Turno guardarTurno(Turno turno){
        Paciente paciente = pacienteService.buscarPorId(turno.getPaciente().getId());
        Odontologo odontologo = odontologoService.buscarPorId(turno.getOdontologo().getId());
        Turno turnoAretonar  = null;
        if(paciente != null && odontologo != null){
            turno.setPaciente(paciente);
            turno.setOdontologo(odontologo);
            turnoAretonar = turnoIDao.guardar(turno);
        }
        return turnoAretonar;
    }

    public Turno buscarPorId(Integer id){
        return turnoIDao.buscarPorId(id);
    }

    public List<Turno> buscarTodos(){
        return turnoIDao.listaTodos();
    }

    public void modificarTurno(Turno turno){
        Paciente paciente = pacienteService.buscarPorId(turno.getPaciente().getId());
        Odontologo odontologo = odontologoService.buscarPorId(turno.getOdontologo().getId());
        Turno turnoAretonar  = null;
        if(paciente != null && odontologo != null){
            turno.setPaciente(paciente);
            turno.setOdontologo(odontologo);
            turnoIDao.modificar(turno);
        }
    }

   public void eliminarTurno(Integer id){
        turnoIDao.eliminar(id);
    }


}
